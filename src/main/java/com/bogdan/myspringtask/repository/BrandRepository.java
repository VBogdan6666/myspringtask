package com.bogdan.myspringtask.repository;

import com.bogdan.myspringtask.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
