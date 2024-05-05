package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        // lowercase the input
        var lowerCase = input.toLowerCase(Locale.ROOT);

        // search for products with the lowercased input in their name or description
        return em.createQuery("SELECT p FROM Product p WHERE lower(p.productName) LIKE :input OR lower(p.description) LIKE :input", Product.class)
                .setParameter("input", "%" + lowerCase + "%")
                .getResultList();
    }

}
