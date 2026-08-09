package com.yeirel.StockCR.repository;

import com.yeirel.StockCR.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByMerchantId(Long merchantId);
}
