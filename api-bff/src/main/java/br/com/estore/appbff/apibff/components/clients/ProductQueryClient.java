package br.com.estore.appbff.apibff.components.clients;

import br.com.estore.appbff.apibff.domain.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-query", url = "${api.products-query}")
public interface ProductQueryClient {

    @GetMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDTO> search(
            @RequestParam("name") final String name,
            @RequestParam("description") final String description,
            @RequestParam("category") final String category,
            @RequestParam("itemsPerPage") final Integer itemPerPage,
            @RequestParam("pageNumber") final Integer pageNumber
    );

}
