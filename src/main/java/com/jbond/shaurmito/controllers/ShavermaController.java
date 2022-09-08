package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Shaverma;
import com.jbond.shaurmito.repo.ShavermaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class ShavermaController {
    private final ShavermaRepository shavermaRepository;

    @Autowired
    public ShavermaController(ShavermaRepository shavermaRepository) {
        this.shavermaRepository = shavermaRepository;
    }

    @GetMapping("/{id}")
    public EntityModel<Shaverma> shavermaById(@PathVariable("id") Long id) {
        Optional<Shaverma> shaverma = shavermaRepository.findById(id);
        /*
        if (!shaverma.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shaverma with id =" + id + " not found");
        }
        */
        return EntityModel.of(shaverma.get(),
                linkTo(methodOn(ShavermaController.class).shavermaById(id)).withSelfRel(),
                linkTo(methodOn(ShavermaController.class).recentShavermas()).withRel("recent"));
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
