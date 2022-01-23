package br.com.estore.ordercommand.schedulers;

import br.com.estore.ordercommand.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReprocessOrderScheduler {

    private final OrderService service;

    public ReprocessOrderScheduler(final OrderService service) {
        this.service = service;
    }

    @Scheduled(cron = "${scheduler.cron}")
    private void execute() {
        log.info("ReprocessOrderScheduler.execute - Start");
        service.republishOrders();
        log.info("ReprocessOrderScheduler.execute - End");
    }

}
