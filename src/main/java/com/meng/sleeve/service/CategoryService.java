package com.meng.sleeve.service;

import com.meng.sleeve.model.Category;
import com.meng.sleeve.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Map<Integer,List<Category>> getCategoryAll(){
        List<Category> roots = categoryRepository.findAllByIsRootOrderByIndex(true);
        List<Category> sbus = categoryRepository.findAllByIsRootOrderByIndex(false);
        Map<Integer, List<Category>> map = new HashMap<>();
        map.put(1,roots);
        map.put(2,sbus);
        return map;
    }

}
