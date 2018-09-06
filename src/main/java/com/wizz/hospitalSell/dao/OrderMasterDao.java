package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
    List<OrderMaster> findByUserOpenidOrderByCreateTimeDesc(String openid);

    Page<OrderMaster> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
