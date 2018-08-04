package com.wizz.hospitalSell.repository;

import com.wizz.hospitalSell.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Cx On 2018/8/4 7:03
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
