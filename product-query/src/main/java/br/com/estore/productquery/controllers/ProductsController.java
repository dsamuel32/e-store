package br.com.estore.productquery.controllers;

import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private final ProductService service;


    public ProductsController(final ProductService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> search(final ProductFilterDTO filters) {
        return service.search(filters);
    }

}
