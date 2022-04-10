/*
package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Vendor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendorControllerTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    VendorRepository vendorRepository;

    private Vendor createTestVendor() {
        Vendor vendor = new Vendor();
        vendor.setEmail("mytestvendor@abcdef.com");
        vendor.setPassword("plainPassword");
        vendor.setVendorName("TEST");
        return vendor;
    }

    @Test
    public void testCreateReadDelete() {
        Vendor vendor = createTestVendor();
        vendorRepository.save(vendor);

        Iterable<Vendor> vendors = vendorRepository.findAll();
        Assertions.assertThat(vendors).extracting(Vendor::getVendorName).containsOnly("TEST");

        vendorRepository.deleteAll();
        Assertions.assertThat(vendorRepository.findAll()).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnVendor() {
        //given
        Vendor vendor = createTestVendor();
        vendorRepository.save(vendor);

        //when
        Optional<Vendor> found = vendorRepository.getVendorById(vendor.getId());

        //then
        Assertions.assertThat(found.isEmpty()).isFalse();
        Assertions.assertThat(found.get().getVendorName()).isEqualTo(vendor.getVendorName());
    }
}
*/
