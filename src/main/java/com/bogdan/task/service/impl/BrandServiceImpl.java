package com.bogdan.task.service.impl;

import com.bogdan.task.entity.Brand;
import com.bogdan.task.repository.BrandRepository;
import com.bogdan.task.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAllBrands(){
        return brandRepository.findAll();
    }


}
