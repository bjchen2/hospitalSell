package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.domain.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis对应dao
 * Created By Cx On 2018/8/5 15:53
 */
@Component
public class CommentInfoDao {

    @Autowired
    CommentMapper commentMapper;

    /**
     * 查询某商品的各个评价
     */
    public Map<String,Object> findScoreMapByProductId(String productId){
        //查询该商品的所有评论
        List<CommentInfo> commentInfos = commentMapper.findScoreByProductId(productId);
        Map<String,Object> result = new HashMap<>();
        //格式为："1"-5，表示1星的有五人
        Map<String,Integer> qualityScore = new HashMap<>();
        Map<String,Integer> tasteScore = new HashMap<>();
        Map<String,Integer> packingScore = new HashMap<>();
        //总评
        double ans = 0;
        //分别表示PackingScore、QualityScore、TasteScore,1-5星人数
        int[] a={0,0,0,0,0},b={0,0,0,0,0},c={0,0,0,0,0};
        for (CommentInfo commentInfo : commentInfos){
            a[commentInfo.getPackingScore()-1]++;
            b[commentInfo.getQualityScore()-1]++;
            c[commentInfo.getTasteScore()-1]++;
        }
        for(int i = 0;i < 5;i++){
            packingScore.put(String.valueOf(i+1),a[i]);
            qualityScore.put(String.valueOf(i+1),b[i]);
            tasteScore.put(String.valueOf(i+1),c[i]);
            ans += (a[i]+b[i]+c[i])*(i+1);
        }
        result.put("result",commentInfos.size()==0?0:ans/3.0/commentInfos.size());
        result.put("qualityScore",qualityScore);
        result.put("tasteScore",tasteScore);
        result.put("packingScore",qualityScore);
        return result;
    }

    public Integer findResultByProductId(String productId){
        Double result = commentMapper.findResultByProductId(productId);
        //Math.round四舍五入返回long，转为string再转为int
        if (result == null) {
            //如果没有人评价
            return 0;
        }
        return Integer.valueOf(String.valueOf(Math.round(result)));
    }
}
