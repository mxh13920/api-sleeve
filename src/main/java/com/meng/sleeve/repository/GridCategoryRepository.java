package com.meng.sleeve.repository;

import com.meng.sleeve.model.GridCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GridCategoryRepository extends JpaRepository<GridCategory,Long> {

    List<GridCategory> findAll();

}
