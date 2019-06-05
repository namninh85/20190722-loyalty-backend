package com.nin.repository;

import com.nin.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT s FROM Store s WHERE s.isActive=true AND s.storeId in (SELECT p.storeId AS storeId FROM ProductInStore p WHERE p.productId=:productId)" +
            " AND lower(s.name) LIKE %"+":storeName"+"% " +
            " AND lower(s.address) LIKE %"+":address"+"% " +
            "AND lower(s.phone) LIKE %"+":phone"+"%")
    List<Store> findByProductId(@Param("productId") Long productId ,@Param("storeName") String storeName ,@Param("address") String address , @Param("phone") String phone);

}
