package br.com.estore.ordercommand.controllers;

import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.dtos.PaymentDTO;
import br.com.estore.ordercommand.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService service;

    public OrderController(final OrderService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@RequestBody @Valid OrderDTO order) {
        return service.create(order);
    }

    @PutMapping(value = "{id}/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO confirm(@PathVariable("id") final Long id, @RequestBody PaymentDTO payment) {
        return service.confirm(id, payment);
    }
}
