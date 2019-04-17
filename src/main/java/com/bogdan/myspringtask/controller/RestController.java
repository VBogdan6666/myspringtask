package com.bogdan.myspringtask.controller;

import com.bogdan.myspringtask.entity.Brand;
import com.bogdan.myspringtask.entity.CarModel;
import com.bogdan.myspringtask.repository.BrandRepository;
import com.bogdan.myspringtask.service.BrandService;
import com.bogdan.myspringtask.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public List<CarModel> showTable(){
        return carModelService.findAllCars();
    }
}
