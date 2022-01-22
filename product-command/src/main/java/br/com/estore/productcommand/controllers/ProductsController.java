package br.com.estore.productcommand.controllers;

import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private final ProductService service;


    public ProductsController(final ProductService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody @Valid ProductDTO product) {
        return service.save(product);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO save(@PathVariable final Long id, @RequestBody @Valid ProductDTO product) {

        final ProductDTO productEnriched = product.toBuilder()
                .id(id)
                .build();

        return service.update(productEnriched);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        service.deleteById(id);
    }

}
