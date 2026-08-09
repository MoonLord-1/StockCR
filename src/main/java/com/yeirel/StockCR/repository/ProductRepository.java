package com.yeirel.StockCR.repository;

import com.yeirel.StockCR.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByMerchantId(Long merchantId);
    List<Product> findByMerchantIdAndCategoryId(Long merchantId, Long categoryId);
    List<Product> findByMerchantIdAndQuantityStockLessThan(Long merchantId, int threshold);
}
