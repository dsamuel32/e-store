package br.com.estore.appbff.apibff.components.proxies;

import br.com.estore.appbff.apibff.components.clients.ProductQueryClient;
import br.com.estore.appbff.apibff.domain.ProductDTO;
import br.com.estore.appbff.apibff.domain.ProductFilterDTO;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQueryProxy {

    private final ProductQueryClient client;

    public ProductQueryProxy(ProductQueryClient client) {
        this.client = client;
    }

    public List<ProductDTO> search(final ProductFilterDTO filters) {
        try {
            return client.search(
                    filters.getName(),
                    filters.getDescription(),
                    filters.getCategory(),
                    filters.getItemsPerPage(),
                    filters.getPageNumber()
            );
        } catch (FeignException e) {
            throw e;
        }
    }

}
