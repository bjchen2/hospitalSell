package com.wizz.hospitalSell.dao;


import com.wizz.hospitalSell.domain.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentInfoRepository extends JpaRepository<CommentInfo, String> {

    List<CommentInfo> findAllByProductIdOrderByCreateTimeDesc(String productId);

    List<CommentInfo> findAllByOrderId(String orderId);
}
