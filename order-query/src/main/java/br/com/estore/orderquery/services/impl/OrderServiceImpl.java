package br.com.estore.orderquery.services.impl;

import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.domain.entities.Order;
import br.com.estore.orderquery.repositories.OrderRepository;
import br.com.estore.orderquery.repositories.OrderRepositoryCustom;
import br.com.estore.orderquery.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository repository;
    private final OrderRepositoryCustom repositoryCustom;

    public OrderServiceImpl(final ModelMapper mapper,
                            final OrderRepository repository,
                            final OrderRepositoryCustom repositoryCustom) {
        this.mapper = mapper;
        this.repository = repository;
        this.repositoryCustom = repositoryCustom;
    }

    @Override
    public void process(OrderDTO order) {
        Optional.of(order)
                .map(o -> mapper.map(o, Order.class))
                .ifPresent(repository::save);
    }

    @Override
    public List<OrderDTO> search(OrderFilterDTO filter) {
        return repositoryCustom.search(filter).stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

}
