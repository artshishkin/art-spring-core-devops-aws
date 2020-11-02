package com.artarkatesoft.awsstudy.services;

import com.artarkatesoft.awsstudy.domain.Product;

import java.util.List;

/**
 * Created by jt on 1/26/16.
 */
public interface ProductService {

    Product getProduct(Integer id);

    List<Product> listProducts();
}
