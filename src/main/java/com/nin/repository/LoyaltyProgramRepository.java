package com.nin.repository;

import com.nin.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram,Long> {
    @Query("SELECT  p FROM LoyaltyProgram p WHERE p.isActive=true " +
            "AND p.startDate <= CAST(:date AS integer) AND p.endDate >= CAST(:date AS integer) ORDER BY p.startDate DESC ")
    List<LoyaltyProgram> findLoyaltyProgramByDate(@Param("date") Long date);
    LoyaltyProgram findByLoyaltyProgramId(Long Id);
    @Query("SELECT  p FROM LoyaltyProgram p WHERE p.isActive=true AND p.loyaltyProgramId=:id " +
            "AND p.startDate <= CAST(:date AS integer) AND p.endDate >= CAST(:date AS integer)")
    LoyaltyProgram findLoyaltyProgramByIdAndDate(@Param("date") Long date , @Param("id") Long Id);
}
