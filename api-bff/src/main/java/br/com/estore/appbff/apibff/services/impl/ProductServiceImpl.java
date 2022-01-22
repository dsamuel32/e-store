package br.com.estore.appbff.apibff.services.impl;

import br.com.estore.appbff.apibff.components.proxies.ProductCommandProxy;
import br.com.estore.appbff.apibff.components.proxies.ProductQueryProxy;
import br.com.estore.appbff.apibff.domain.ProductDTO;
import br.com.estore.appbff.apibff.domain.ProductFilterDTO;
import br.com.estore.appbff.apibff.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductQueryProxy queryProxy;
    private final ProductCommandProxy commandProxy;

    public ProductServiceImpl(final ProductQueryProxy queryProxy,
                              final ProductCommandProxy commandProxy) {
        this.queryProxy = queryProxy;
        this.commandProxy = commandProxy;
    }

    @Override
    public List<ProductDTO> search(final ProductFilterDTO filters) {
        return queryProxy.search(filters);
    }

    @Override
    public ProductDTO save(final ProductDTO product) {
        return commandProxy.save(product);
    }

    @Override
    public ProductDTO update(final ProductDTO product) {
        return commandProxy.update(product);
    }

    @Override
    public void delete(final Long id) {
        commandProxy.delete(id);
    }

}
