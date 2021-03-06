package com.bogdan.task.service.impl;

import com.bogdan.task.entity.CarModel;
import com.bogdan.task.repository.CarModelRepository;
import com.bogdan.task.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    @Autowired
    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public List<CarModel> findAllCarModels(){
        return carModelRepository.findAll();
    }

    @Override
    public void addNewOrEditCarModel(CarModel carModel){
        if(!carModel.getName().isEmpty() && carModel.getBrand().getId()!=null && !carModel.getBrand().getName().isEmpty()){
            carModelRepository.save(carModel);
        }
    }

    @Override
    public void delCarModel(Long id){
        if(findCarModel(id).isPresent()){
            carModelRepository.deleteById(id);
        }
    }

    @Override
    public Optional<CarModel> findCarModel(Long id){
        return carModelRepository.findById(id);
    }


}
