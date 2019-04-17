package com.bogdan.myspringtask.service.Impl;

import com.bogdan.myspringtask.entity.Brand;
import com.bogdan.myspringtask.repository.BrandRepository;
import com.bogdan.myspringtask.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAllBrands(){
        return brandRepository.findAll();
    }


}
