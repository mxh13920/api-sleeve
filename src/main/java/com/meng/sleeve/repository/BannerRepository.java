package com.meng.sleeve.repository;

import com.meng.sleeve.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Long> {
    Banner findOneById(Long id);
    Banner findOneByName(String name);
}