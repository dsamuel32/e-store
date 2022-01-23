package br.com.estore.appbff.apibff.components.clients;

import br.com.estore.appbff.apibff.domain.products.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-command", url = "${api.products-command}")
public interface ProductCommandClient {

    @PostMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductDTO save(@RequestBody final ProductDTO product);

    @PutMapping(value = "v1/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductDTO update(@PathVariable("id") final Long id, @RequestBody final ProductDTO product);

    @DeleteMapping(value = "v1/products/{id}")
    void delete(@PathVariable("id") final Long id);

}
