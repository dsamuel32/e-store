package br.com.estore.productcommand.schedulers;

import br.com.estore.productcommand.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReprocessProductScheduler {

    private final ProductService productService;

    public ReprocessProductScheduler(final ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "${scheduler.cron}")
    private void execute() {
        log.info("ReprocessProductScheduler.execute - Start");
        productService.republishProducts();
        log.info("ReprocessProductScheduler.execute - End");
    }

}
