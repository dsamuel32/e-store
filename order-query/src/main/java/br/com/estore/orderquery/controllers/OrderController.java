package br.com.estore.orderquery.controllers;

import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService service;

    public OrderController(final OrderService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> search(OrderFilterDTO filter) {
        return service.search(filter);
    }
}
