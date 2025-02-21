package com.example.g130ministore.repository;

import com.example.g130ministore.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByCode(String code);


    @Query("SELECT b FROM Brand b " +
            "WHERE b.code = :search OR b.name = :search")
    Optional<Brand> search(String search);
}
