package com.perfumes.Perfumes.assemblers;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.controller.ProveedorController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>>  {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorController.class).findById(proveedor.getIdProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).list()).withRel("proveedores")
        );
    }
}
