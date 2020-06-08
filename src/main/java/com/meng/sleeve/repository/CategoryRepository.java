package com.meng.sleeve.repository;

import com.meng.sleeve.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category,Long> {

    List<Category> findAllByIsRootOrderByIndex(Boolean isRoot);

}
