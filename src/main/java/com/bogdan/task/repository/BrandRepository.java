package com.bogdan.task.repository;

import com.bogdan.task.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
