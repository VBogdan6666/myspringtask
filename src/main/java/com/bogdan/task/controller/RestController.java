package com.bogdan.task.controller;

import com.bogdan.task.entity.CarModel;
import com.bogdan.task.service.BrandService;
import com.bogdan.task.service.CarModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CarModelService carModelService;

    @GetMapping("/rest")
    public String restIndex(){
        return "rest";
    }

    @GetMapping("/rest/indexData")
    @ResponseBody
    public ObjectNode showTable(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("cars",carModelService.findAllCars());
        objectNode.putPOJO("brands",brandService.findAllBrands());
        return objectNode;
    }

    @DeleteMapping("/rest/delCarModel/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id) {
        carModelService.delCarModel(id);
    }

    @PostMapping("/rest/addCarModel")
    @ResponseBody
    public void addCarModel(@RequestBody CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
    }

    @PutMapping("/rest/editCarModel")
    @ResponseBody
    public void editCarModel(@RequestBody CarModel carModel) {
        carModelService.addNewOrEditCarModel(carModel);
    }

}
