package com.meng.sleeve.service;

import com.meng.sleeve.model.Activity;
import com.meng.sleeve.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity getByName(String name){
        return activityRepository.findByName(name);
    }

}
