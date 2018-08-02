package com.wizz.hospitalSell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 评价信息表
 * Created By Cx On 2018/7/30 18:30
 */
@Entity
@Table(name = "",schema = "")
public class CommentInfo {
    //评论id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//评论区的id设置为自增。
    private String commentId;
    //商品质量评价 0-5
    private Integer qualityScore;
    //商品口味评价
    private Integer tasteScore;
    //商品包装评价
    private Integer packingScore;
    //菜品id
    private String productId;
    //用户微信名
    private String wxName;
    //用户微信openid
    private String openid;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Integer qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getTasteScore() {
        return tasteScore;
    }

    public void setTasteScore(Integer tasteScore) {
        this.tasteScore = tasteScore;
    }

    public Integer getPackingScore() {
        return packingScore;
    }

    public void setPackingScore(Integer packingScore) {
        this.packingScore = packingScore;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
