package com.bogdan.myspringtask.service.Impl;

import com.bogdan.myspringtask.entity.CarModel;
import com.bogdan.myspringtask.repository.CarModelRepository;
import com.bogdan.myspringtask.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public List<CarModel> findAllCars(){
        return carModelRepository.findAll();
    }

    @Override
    public void addNewOrEditCarModel(CarModel carModel){
        carModelRepository.save(carModel);
    }

    @Override
    public void delCarModel(Long id){
        carModelRepository.deleteById(id);
    }

    @Override
    public CarModel findCarModel(Long id){
        Optional<CarModel> carModelOptional=carModelRepository.findById(id);
        return carModelOptional.orElse(null);
    }


}
