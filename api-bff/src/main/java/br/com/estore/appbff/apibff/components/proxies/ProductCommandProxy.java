package br.com.estore.appbff.apibff.components.proxies;

import br.com.estore.appbff.apibff.components.clients.ProductCommandClient;
import br.com.estore.appbff.apibff.domain.products.ProductDTO;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandProxy {

    private final ProductCommandClient client;

    public ProductCommandProxy(final ProductCommandClient client) {
        this.client = client;
    }

    public ProductDTO save(final ProductDTO product) {
        try {
            return client.save(product);
        } catch (FeignException e) {
            throw e;
        }
    }

    public ProductDTO update(final ProductDTO product) {
        try {
            return client.update(product.getId(), product);
        } catch (FeignException e) {
            throw e;
        }
    }

    public void delete(final Long id) {
        try {
            client.delete(id);
        } catch (FeignException e) {
            throw e;
        }
    }
}
