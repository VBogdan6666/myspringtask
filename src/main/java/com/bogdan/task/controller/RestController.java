package com.bogdan.task.controller;

import com.bogdan.task.entity.CarModel;
import com.bogdan.task.service.BrandService;
import com.bogdan.task.service.CarModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class RestController {

    @Autowired
    public MessageSource messageSource;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CarModelService carModelService;

    @GetMapping("/rest")
    public String showRestPage(){
        return "rest";
    }

    @GetMapping("/rest/index-data")
    @ResponseBody
    public ObjectNode getData(Locale locale){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("cars",carModelService.findAllCarModels());
        objectNode.putPOJO("brands",brandService.findAllBrands());
        objectNode.putPOJO("edit",messageSource.getMessage("edit", null, locale));
        objectNode.putPOJO("addLoc",messageSource.getMessage("add", null, locale));
        return objectNode;
    }

    @DeleteMapping("/rest/del-car-model/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id) {
        carModelService.delCarModel(id);
    }

    @PostMapping("/rest/add-car-model")
    @ResponseBody
    public void addCarModel(@RequestBody CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
    }

    @PutMapping("/rest/edit-car-model")
    @ResponseBody
    public void editCarModel(@RequestBody CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
    }

}
