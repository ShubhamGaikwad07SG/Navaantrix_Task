package com.Navaantrix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Navaantrix.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
