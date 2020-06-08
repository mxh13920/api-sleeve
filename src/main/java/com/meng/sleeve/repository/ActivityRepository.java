package com.meng.sleeve.repository;

import com.meng.sleeve.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Activity findByName(String name);
}
