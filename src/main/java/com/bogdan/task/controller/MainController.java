package com.bogdan.task.controller;

import com.bogdan.task.entity.CarModel;
import com.bogdan.task.service.BrandService;
import com.bogdan.task.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

   private static final String REDIRECT="redirect:/";

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public String showMainPage(Model model) {

        model.addAttribute("cars", carModelService.findAllCarModels());
        model.addAttribute("carModel", new CarModel());
        model.addAttribute("brands", brandService.findAllBrands());
        return "index";
    }

    @PostMapping("/add-car-model")
    public String addNewCarModel(CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
        return REDIRECT;
    }

    @DeleteMapping("/del-car-model/{id}")
    public String deleteCarModel(@PathVariable("id") Long id) {
        carModelService.delCarModel(id);
        return REDIRECT;
    }

    @GetMapping("/edit-car-model/{id}")
    public String showEditPage(Model model,
                               @PathVariable("id") Long id) {
        Optional<CarModel> carModel = carModelService.findCarModel(id);
        if(carModel.isPresent()){
            model.addAttribute("carModel", carModel.get());
            model.addAttribute("brands", brandService.findAllBrands());
            return "car-model-edit";
        }else {
            return REDIRECT;
        }
    }

    @PutMapping("/edit-car-model")
    public String editCarModel(CarModel carModel){
        carModelService.addNewOrEditCarModel(carModel);
        return REDIRECT;
    }
}
