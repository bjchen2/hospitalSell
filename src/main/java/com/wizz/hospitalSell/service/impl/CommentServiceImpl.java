package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.dto.ProductCommentDto;
import com.wizz.hospitalSell.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Cx On 2018/8/2 9:49
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public List<CommentInfo> findInfosByProductId(String productId) {
        return null;
    }

    @Override
    public List<ProductCommentDto> findAllDtos() {
        return null;
    }

    @Override
    public CommentInfo create(CommentInfo productInfo) {
        return null;
    }
}
