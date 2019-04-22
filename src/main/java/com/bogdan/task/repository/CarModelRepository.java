package com.bogdan.task.repository;

import com.bogdan.task.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
}
