package br.com.estore.productcommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProductCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCommandApplication.class, args);
    }

}
