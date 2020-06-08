package com.meng.sleeve.vo;

import com.meng.sleeve.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CatrgoryAllVO {
    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> sbus;

    public CatrgoryAllVO(Map<Integer, List<Category>> map) {

//        this.roots = map.get(1).stream()
//                .map(r -> {
//                    return new CategoryPureVO();
//                }).collect(Collectors.toList());
//        this.sbus = map.get(2).stream()
//                .map(r -> {
//                    return new CategoryPureVO();
//                }).collect(Collectors.toList());

        this.roots = map.get(1).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
        this.sbus = map.get(2).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
    }

}
