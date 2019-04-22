package com.bogdan.task.service;

import com.bogdan.task.entity.CarModel;

import java.util.List;

public interface CarModelService {

    List<CarModel> findAllCars();

    void addNewOrEditCarModel(CarModel carModel);

    void delCarModel(Long id);

    CarModel findCarModel(Long id);

}
