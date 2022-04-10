//package com.magazin.demo.controller;
//
//import com.magazin.demo.model.Product;
//import com.magazin.demo.model.Vendor;
//import com.magazin.demo.service.impl.ProductServiceImpl;
//import com.magazin.demo.service.impl.VendorServiceImpl;
//import org.checkerframework.checker.nullness.Opt;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(VendorController.class)
//public class VendorControllerTests {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private VendorServiceImpl service;
//
//    private static final Logger log = LoggerFactory.getLogger(VendorControllerTests.class);
//
//    private Vendor createTestVendor() {
//        Vendor vendor = new Vendor();
//        vendor.setEmail("mytestvendor@abcdef.com");
//        vendor.setPassword("plainPassword");
//        vendor.setVendorName("TEST");
//        return vendor;
//    }
//
//    @Test
//    public void givenVendors_whenGetVendors_thenReturnJsonArray() throws Exception {
//        Vendor vendor = createTestVendor();
//        log.info("New vendor created");
//        List<Vendor> allProducts = Arrays.asList(vendor);
//
//        given(service.findAll()).willReturn(allProducts);
//        log.info("Service findAll returns the expected list");
//
//        mvc.perform(get("/vendors")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].vendorName", is(vendor.getVendorName())));
//        log.info("GET on /vendors returns the expected json array");
//    }
//
//}
