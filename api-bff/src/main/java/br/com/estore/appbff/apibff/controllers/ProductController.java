package br.com.estore.appbff.apibff.controllers;

import br.com.estore.appbff.apibff.domain.products.ProductDTO;
import br.com.estore.appbff.apibff.domain.products.ProductFilterDTO;
import br.com.estore.appbff.apibff.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService service;

    public ProductController(final ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDTO> search(final ProductFilterDTO filters) {
        return service.search(filters);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO save(@RequestBody @Valid ProductDTO product) {
        return service.save(product);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO save(@PathVariable("id") final Long id, @RequestBody @Valid ProductDTO product) {
        final ProductDTO productEnriched = product.toBuilder().id(id).build();
        return service.save(productEnriched);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") final Long id) {
        service.delete(id);
    }

}
