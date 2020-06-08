package com.meng.sleeve.service;

import com.meng.sleeve.model.Theme;
import com.meng.sleeve.repository.ThemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemSerive {

    @Autowired
    private ThemRepository themRepository;

    public List<Theme> getThemByName(List<String> name) {
        return themRepository.findByName(name);
    }

    public Optional<Theme> getOneTheme(String name) {
        return themRepository.findByName(name);
    }

    public List<Theme> getAllTheme(){
       return themRepository.findAll();
    };
}
