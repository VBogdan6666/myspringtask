package com.bogdan.myspringtask.service;

import com.bogdan.myspringtask.entity.CarModel;

import java.util.List;

public interface CarModelService {

    List<CarModel> findAllCars();

    void addNewOrEditCarModel(CarModel carModel);

    void delCarModel(Long id);

    CarModel findCarModel(Long id);

}
