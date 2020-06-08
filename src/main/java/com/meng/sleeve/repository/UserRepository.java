package com.meng.sleeve.repository;

import com.meng.sleeve.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findByOpenid(String openId);

    User findFirstById(Long id);

    User findByUnifyUid(Long unifyUid);
}
