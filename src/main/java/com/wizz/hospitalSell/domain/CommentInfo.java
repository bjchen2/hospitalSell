package com.wizz.hospitalSell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 评价信息表
 * Created By Cx On 2018/7/30 18:30
 */
@Entity
@Data
@DynamicInsert
public class CommentInfo implements Serializable{

    private static final long serialVersionUID = 6447964140069063033L;
    //评论id
    @Id
    private String commentId;
    //商品质量评价 0-5
    private Integer qualityScore;
    //商品口味评价
    private Integer tasteScore;
    //商品包装评价
    private Integer packingScore;
    //菜品id
    private String productId;
    //用户微信openid
    private String userOpenid;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}

