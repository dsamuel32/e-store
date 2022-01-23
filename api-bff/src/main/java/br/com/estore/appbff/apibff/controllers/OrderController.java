package br.com.estore.appbff.apibff.controllers;

import br.com.estore.appbff.apibff.domain.orders.command.OrderCommandDTO;
import br.com.estore.appbff.apibff.domain.orders.command.PaymentDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderFilterDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderQueryDTO;
import br.com.estore.appbff.apibff.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<OrderQueryDTO> search(final OrderFilterDTO filter) {
        return service.search(filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCommandDTO create(@RequestBody final OrderCommandDTO order) {
        return service.create(order);
    }

    @PutMapping(value = "{id}/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderCommandDTO confirm(@PathVariable("id") Long id, @RequestBody final PaymentDTO payment) {
        return service.confirm(id, payment);
    }

}
