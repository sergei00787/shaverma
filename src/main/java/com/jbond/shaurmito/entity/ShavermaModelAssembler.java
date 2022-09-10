package com.jbond.shaurmito.entity;

import com.jbond.shaurmito.controllers.ShavermaController;
import com.jbond.shaurmito.repo.ShavermaRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShavermaModelAssembler implements RepresentationModelAssembler<Shaverma, EntityModel<Shaverma>> {
    @Override
    public EntityModel<Shaverma> toModel(Shaverma entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ShavermaController.class).shavermaById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ShavermaRepository.class).findAll()).slash("shavermas").withRel("allshavermas"));
    }
}
