package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Shaverma;
import com.jbond.shaurmito.repo.ShavermaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;

import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.core.WebHandler.linkTo;


@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class ShavermaController {
    @Autowired
    Links entityLinks;
    private final ShavermaRepository shavermaRepository;

    @Autowired
    public ShavermaController(ShavermaRepository shavermaRepository) {
        this.shavermaRepository = shavermaRepository;
    }

    @GetMapping("/{id}")
    public EntityModel<Shaverma> shavermaById(@PathVariable("id") Long id) {
        Optional<Shaverma> shaverma = shavermaRepository.findById(id);
        return shaverma.isPresent() ?
                EntityModel.of(shaverma.get(),
                        linkTo(methodOn(ShavermaController.class).one(id)).withSelfRel(),
                        linkTo(methodOn(ShavermaController.class).all()).withRel("employees")):

        new ResponseEntity<Shaverma>( HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recent")
    public Iterable<Shaverma> recentShavermas() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return shavermaRepository.findAll(page).getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Shaverma processShaverma(@RequestBody Shaverma shaverma) {
        log.info(shaverma.toString());

        return shavermaRepository.save(shaverma);
    }
}
