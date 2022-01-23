package br.com.estore.ordercommand.schedulers;

import br.com.estore.ordercommand.domain.entities.Order;
import br.com.estore.ordercommand.domain.entities.Status;
import br.com.estore.ordercommand.domain.enums.StatusEnum;
import br.com.estore.ordercommand.repositories.OrderRepository;
import br.com.estore.ordercommand.repositories.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//SCHEDULER APENAS PARA SIMULAR O FLUXO DA ORDEM.
@Service
@Slf4j
public class SchedulerFakeOrderFlow {

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;

    public SchedulerFakeOrderFlow(OrderRepository orderRepository, StatusRepository statusRepository) {
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
    }

    @Scheduled(cron = "${scheduler.cron}")
    private void execute() throws InterruptedException {
        log.info("Fake scheduler - Start");
        final List<Order> orders = orderRepository.findAll();
        confirmOrder(orders);
        Thread.sleep(10000);
        completedOrder(orders);
        Thread.sleep(10000);
        log.info("Fake scheduler - End");
    }

    private void confirmOrder(final List<Order> orders) {

        orders.stream()
                .filter(order -> isSameStatus(order.getStatus(), StatusEnum.WAIT_PAYMENT))
                .map(order -> order.toBuilder().status(getStatus(StatusEnum.CONFIRMED.name())).published(false).build())
                .forEach(orderRepository::save);
    }

    private void completedOrder(final List<Order> orders) {
        orders.stream()
                .filter(order -> isSameStatus(order.getStatus(), StatusEnum.CONFIRMED))
                .map(order -> order.toBuilder().status(getStatus(StatusEnum.COMPLETED.name())).published(false).build())
                .forEach(orderRepository::save);
    }

    private Status getStatus(final String statusCode) {
        return statusRepository.findByCode(statusCode);
    }

    private Boolean isSameStatus(final Status status, StatusEnum statusEnum) {
        return status.getCode().equals(statusEnum.name());
    }

}
