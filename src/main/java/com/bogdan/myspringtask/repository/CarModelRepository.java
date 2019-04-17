package com.bogdan.myspringtask.repository;

import com.bogdan.myspringtask.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
}
