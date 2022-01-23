package br.com.estore.orderquery.repositories.impl;

import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.domain.entities.Order;
import br.com.estore.orderquery.repositories.OrderRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final MongoTemplate template;

    public OrderRepositoryCustomImpl(final MongoTemplate template) {
        this.template = template;
    }

    @Override
    public List<Order> search(OrderFilterDTO filter) {
        final Query querySearch = createQuery(filter);
        return template.find(querySearch, Order.class);
    }


    private Query createQuery(final OrderFilterDTO filter) {
        final Query query = new Query();
        final Pageable pageable = PageRequest.of(filter.getPageNumber() - 1, filter.getItemsPerPage());
        final List<Criteria> optionalParameters = createOptionalParameters(filter);
        optionalParameters.forEach(query::addCriteria);
        return query.with(pageable);
    }

    private List<Criteria> createOptionalParameters(final OrderFilterDTO filter) {
        return Arrays.asList(
                        Optional.ofNullable(filter.getEmail()).map(name -> Criteria.where("customer.email").regex(name)).orElse(null),
                        Optional.ofNullable(filter.getStatus()).map(description -> Criteria.where("status.code").is(description)).orElse(null),
                        createRangePriceFilter(filter)
                ).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Criteria createRangePriceFilter(final OrderFilterDTO filter) {
        if (hasPriceFilter(filter)) {
            return Criteria.where("price").lte(filter.getMinPrice()).gte(filter.getMaxPrice());
        }
        return null;
    }

    private Boolean hasPriceFilter(final OrderFilterDTO filter) {
        return filter.getMinPrice() != null && filter.getMaxPrice() != null;
    }

}
