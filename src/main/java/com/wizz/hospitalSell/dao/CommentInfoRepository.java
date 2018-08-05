package com.wizz.hospitalSell.dao;


import com.wizz.hospitalSell.domain.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface CommentInfoRepository extends JpaRepository<CommentInfo,String> {

    CommentInfo getByCommentId(String commentId);
    List<CommentInfo> findAllByProductIdOrderByCreateTime(String productId);
}
