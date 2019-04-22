package com.bogdan.task.controller;

import com.bogdan.task.entity.CarModel;
import com.bogdan.task.service.BrandService;
import com.bogdan.task.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {


    @Autowired
    private CarModelService carModelService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public String showMainPage(Model model) {
        //System.out.println(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("cars", carModelService.findAllCars());
        model.addAttribute("carModel", new CarModel());
        model.addAttribute("brands", brandService.findAllBrands());
        return "index";
    }

    @PostMapping("/addCarModel")
    public String addNewCarModel(CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
        return "redirect:/";
    }

    @DeleteMapping("/delCarModel/{id}")
    public String deleteCarModel(@PathVariable("id") Long id) {
        carModelService.delCarModel(id);
        return "redirect:/";
    }

    @GetMapping("/editCarModel/{id}")
    public String showEditPage(Model model,
                               @PathVariable("id") Long id) {
        CarModel carModel = carModelService.findCarModel(id);
        if(carModel != null){
            model.addAttribute("carModel", carModel);
            model.addAttribute("brands", brandService.findAllBrands());
            return "editCarModelForm";
        }else {
            return "redirect:/";
        }
    }

    @PutMapping("/editCarModel")
    public String editCarModel(CarModel carModel){
        carModelService.addNewOrEditCarModel(carModel);
        return "redirect:/";
    }
}
