package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByOpenid(String openid, Pageable pageable);//根据买家的openid来返回订单管理，并分页处理。
}
