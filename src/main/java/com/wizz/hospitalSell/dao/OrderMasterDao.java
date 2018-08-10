package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByUserOpenid(String openid, Pageable pageable);//根据买家的openid来返回订单管理，并分页处理。
    List<OrderMaster> findByUserOpenid(String openid);
    Page<OrderMaster> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
