package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreateReadDelete() {
        Product product = new Product("testProduct",50f,true);

        productRepository.save(product);

        Iterable<Product> products = productRepository.findAll();
        Assertions.assertThat(products).extracting(Product::getName).containsOnly("testProduct");

        productRepository.deleteAll();
        Assertions.assertThat(productRepository.findAll()).isEmpty();
    }

    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        Product product = new Product("testProduct",50f,true);
        entityManager.persist(product);
        entityManager.flush();

        // when
        Optional<Product> found = productRepository.getProductByName(product.getName());

        // then
        Assertions.assertThat(found.isEmpty()).isFalse();
        Assertions.assertThat(found.get().getName()).isEqualTo(product.getName());
    }
}
