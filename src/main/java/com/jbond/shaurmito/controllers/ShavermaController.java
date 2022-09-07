package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Shaverma;
import com.jbond.shaurmito.repo.ShavermaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class ShavermaController {
    @Autowired
    EntityLinks entityLinks;
    private final ShavermaRepository shavermaRepository;

    @Autowired
    public ShavermaController(ShavermaRepository shavermaRepository) {
        this.shavermaRepository = shavermaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shaverma> shavermaById(@PathVariable("id") Long id) {
        Optional<Shaverma> shaverma = shavermaRepository.findById(id);
        return shaverma.isPresent() ?
                new ResponseEntity<Shaverma>(shaverma.get(), HttpStatus.OK) :
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
