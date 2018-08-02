package com.wizz.hospitalSell.dto;

import java.util.Map;

/**
 * 商品评价有关
 * Created By Cx On 2018/8/1 22:36
 */
public class ProductCommentDto {

    //商品Id
    String productId;
    //商品名称
    String productName;
    //商品质量评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<Integer,Integer> qualityScore;
    //商品口味评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<Integer,Integer> tasteScore;
    //商品包装评价,包含五个元素,key分别为1-5，value为1-5星的人数
    Map<Integer,Integer> packingScore;
    //总评，总星数/总人数
    Double result;
}
