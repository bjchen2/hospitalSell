package com.wizz.hospitalSell.dao;


import com.wizz.hospitalSell.domain.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentInfoDao extends JpaRepository<CommentInfo,String> {
    CommentInfo getByCommentId(String commentId);
    List<CommentInfo> findAllByCommentIdAndCreateTimeOrderByCreateTime(String commentId, Date createTime);
}
