package com.meng.sleeve.repository;

import com.meng.sleeve.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThemRepository extends JpaRepository<Theme,Long> {

    @Query("select  t from Theme t where t.name in (:name)")
    List<Theme> findByName(List<String> name);

    Optional<Theme> findByName(String name);

    List<Theme> findAll();
}
