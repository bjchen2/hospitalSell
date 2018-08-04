package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetaiDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByDetailId(String detailId);
}
