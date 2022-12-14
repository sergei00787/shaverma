package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Shaverma;
import com.jbond.shaurmito.entity.ShavermaModelAssembler;
import com.jbond.shaurmito.repo.ShavermaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class ShavermaController {
    private final ShavermaRepository shavermaRepository;
    private final ShavermaModelAssembler shavermaModelAssembler;

    @Autowired
    public ShavermaController(ShavermaRepository shavermaRepository,
                              ShavermaModelAssembler shavermaModelAssembler) {
        this.shavermaRepository = shavermaRepository;
        this.shavermaModelAssembler = shavermaModelAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<Shaverma> shavermaById(@PathVariable("id") Long id) {
        Optional<Shaverma> shaverma = shavermaRepository.findById(id);
        return EntityModel.of(shaverma.get(),
                linkTo(methodOn(ShavermaController.class).shavermaById(id)).withSelfRel(),
                linkTo(methodOn(ShavermaController.class).recentShavermas()).withRel("recent"));
    }

    @GetMapping("/recent")
    public CollectionModel<EntityModel<Shaverma>> recentShavermas() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Shaverma> listShaverma = shavermaRepository.findAll(page).getContent();
        return CollectionModel.of(listShaverma.stream()
                        .map(shavermaModelAssembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ShavermaController.class).recentShavermas()).withSelfRel());
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Shaverma processShaverma(@RequestBody Shaverma shaverma) {
        log.info(shaverma.toString());

        return shavermaRepository.save(shaverma);
    }
}
