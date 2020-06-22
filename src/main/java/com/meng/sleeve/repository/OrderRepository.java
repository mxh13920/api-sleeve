package com.meng.sleeve.repository;

import com.meng.sleeve.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Page<Order> findByExpiredTimeGreaterThanAndStatusAndAndUserId(Date now, Integer status, Long uid, Pageable pageable);

    Page<Order> findByUserId(Long uid,Pageable pageable);

    Page<Order> findByStatusAndUserId(int status,Long uid,Pageable pageable);

    Optional<Order> findByUserIdAndId(Long uid,Long oid);
}
