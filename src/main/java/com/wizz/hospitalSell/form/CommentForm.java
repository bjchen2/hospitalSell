package com.wizz.hospitalSell.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 注意，作为接收Json的类，必须要有Get/Set方法
 * Created By Cx On 2018/8/7 15:43
 */
@Data
public class CommentForm {
    //商品质量评价 0-5
    private Integer qualityScore;
    //商品口味评价
    private Integer tasteScore;
    //商品包装评价
    private Integer packingScore;
    //菜品id
//    @NotBlank(message = "商品Id不能为空")
    private String productId;
    //用户微信openid
//    @NotBlank(message = "用户openid不能为空")
    private String userOpenid;
}
