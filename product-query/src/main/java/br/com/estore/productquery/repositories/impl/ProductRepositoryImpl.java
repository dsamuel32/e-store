package br.com.estore.productquery.repositories.impl;

import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.domain.entities.Product;
import br.com.estore.productquery.repositories.ProductCustomRepository;
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
public class ProductRepositoryImpl implements ProductCustomRepository {

    private final MongoTemplate mongoTemplate;

    public ProductRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Product> search(ProductFilterDTO filters) {
        final Query querySearch = createQuery(filters);
        return mongoTemplate.find(querySearch, Product.class);
    }


    private Query createQuery(final ProductFilterDTO filters) {
        final Query query = new Query();
        final Pageable pageable = PageRequest.of(filters.getPageNumber() - 1, filters.getItemsPerPage());
        final List<Criteria> optionalParameters = createOptionalParameters(filters);
        optionalParameters.forEach(query::addCriteria);
        return query.with(pageable);
    }

    private List<Criteria> createOptionalParameters(final ProductFilterDTO filter) {
        return Arrays.asList(
                        Optional.ofNullable(filter.getName()).map(name -> Criteria.where("name").regex(name)).orElse(null),
                        Optional.ofNullable(filter.getDescription()).map(description -> Criteria.where("description").regex(description)).orElse(null),
                        Optional.ofNullable(filter.getCategory()).map(category -> Criteria.where("category.description").regex(category)).orElse(null)
                ).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
