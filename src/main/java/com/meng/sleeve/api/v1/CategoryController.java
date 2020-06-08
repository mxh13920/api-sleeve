package com.meng.sleeve.api.v1;

import com.meng.sleeve.model.Category;
import com.meng.sleeve.model.GridCategory;
import com.meng.sleeve.service.CategoryService;
import com.meng.sleeve.service.GridCategoryService;
import com.meng.sleeve.vo.CatrgoryAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GridCategoryService gridCategoryService;

    @GetMapping("/all")
    public CatrgoryAllVO getCategory() {
        Map<Integer, List<Category>> categoryAll = categoryService.getCategoryAll();
        return new CatrgoryAllVO(categoryAll);
    }

    @GetMapping("/grid/all")
    public List<GridCategory> getGridCategory() {
        return gridCategoryService.getGridCategoryAll();
    }

}
