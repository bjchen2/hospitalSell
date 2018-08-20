package com.wizz.hospitalSell.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 商品评价有关
 * Created By Cx On 2018/8/1 22:36
 */
@Data
public class ProductCommentDto implements Serializable{

    private static final long serialVersionUID = -557337745813375026L;
    //商品Id
    String productId;
    //商品名称
    String productName;
    //freemarker数组中的值必须是字符串，为了freemarker能获取，key必须是String
    //商品质量评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<String,Integer> qualityScore;
    //商品口味评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<String,Integer> tasteScore;
    //商品包装评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<String,Integer> packingScore;
    //总评，总星数/总人数
    Double result;
}
