package com.bogdan.task.service;

import com.bogdan.task.entity.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelService {

    List<CarModel> findAllCars();

    void addNewOrEditCarModel(CarModel carModel);

    void delCarModel(Long id);

    Optional<CarModel> findCarModel(Long id);

}
