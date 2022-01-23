package br.com.estore.appbff.apibff.components.clients;

import br.com.estore.appbff.apibff.domain.orders.query.OrderQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "order-query", url = "${api.orders-query}")
public interface OrderQueryClient {

    @GetMapping(value = "v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrderQueryDTO> search(
            @RequestParam("email") final String email,
            @RequestParam("status") final String status,
            @RequestParam("minPrice") final BigDecimal minPrice,
            @RequestParam("maxPrice") final BigDecimal maxPrice,
            @RequestParam("itemsPerPage") final Integer itemPerPage,
            @RequestParam("pageNumber") final Integer pageNumber
    );

}
