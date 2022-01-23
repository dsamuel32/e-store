package br.com.estore.appbff.apibff.components.clients;

import br.com.estore.appbff.apibff.domain.orders.command.OrderCommandDTO;
import br.com.estore.appbff.apibff.domain.orders.command.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-command", url = "${api.orders-command}")
public interface OrderCommandClient {

    @PostMapping(value = "v1/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderCommandDTO create(@RequestBody OrderCommandDTO order);

    @PutMapping(value = "v1/orders/{id}/confirm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderCommandDTO confirm(@PathVariable("id") final Long id,
                            @RequestBody  final PaymentDTO payment);
}
