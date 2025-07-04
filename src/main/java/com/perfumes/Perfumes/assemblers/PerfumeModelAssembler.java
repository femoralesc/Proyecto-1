package com.perfumes.Perfumes.assemblers;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.controller.PerfumeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component

public class PerfumeModelAssembler implements RepresentationModelAssembler<Perfume, EntityModel<Perfume>> {

    @Override
    public EntityModel<Perfume> toModel(Perfume perfume) {
        return EntityModel.of(perfume,
                linkTo(methodOn(PerfumeController.class).findById(perfume.getIdPerfume())).withSelfRel(),
                linkTo(methodOn(PerfumeController.class).list()).withRel("perfumes")
        );
    }
}
