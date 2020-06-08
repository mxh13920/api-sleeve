package com.meng.sleeve.service;

import com.meng.sleeve.model.Category;
import com.meng.sleeve.model.GridCategory;
import com.meng.sleeve.repository.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryService {

    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    public List<GridCategory> getGridCategoryAll(){
        return  gridCategoryRepository.findAll();
    }


}
