package com.meng.sleeve.service;

import com.meng.sleeve.model.Banner;
import com.meng.sleeve.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    @Autowired
    BannerRepository bannerRepository;

    public Banner getByName(String name) {
        Banner oneByName = bannerRepository.findOneByName(name);
        return oneByName;
    }
}
