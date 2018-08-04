package com.wizz.hospitalSell.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaTemplateService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.wizz.hospitalSell.config.WeChatAccountConfig;
import com.wizz.hospitalSell.constant.PushMessageConstant;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 后台为PC端，无法获取formId（需在微信端获取），所以无法推送消息
 * Created By Cx On 2018/8/3 21:25
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    WxMaService wxMaService;
    @Autowired
    WeChatAccountConfig weChatAccountConfig;

    @Override
    public void orderFinish(OrderDto orderDto) {
        WxMaTemplateService templateService = wxMaService.getTemplateService();
        WxMaTemplateMessage wxMpTemplateMessage = new WxMaTemplateMessage();
        //TODO 后台为PC端，无法获取formId（需在微信端获取），所以无法推送消息
        //wxMpTemplateMessage.setToUser(orderDto.getUserOpenid());
        wxMpTemplateMessage.setToUser(orderDto.getUserOpenid());
        wxMpTemplateMessage.setTemplateId(weChatAccountConfig.getTemplateId().get("orderStatus"));
        //添加推送消息数据
        String orderName = orderDto.getOrderDetailList().get(0).getProductName().concat("......");
        wxMpTemplateMessage.addData(new WxMaTemplateMessage.Data("keyword1", orderDto.getOrderId()));
        wxMpTemplateMessage.addData(new WxMaTemplateMessage.Data("keyword2", orderName));
        wxMpTemplateMessage.addData(new WxMaTemplateMessage.Data("keyword3", "￥"+orderDto.getOrderAmount()));
        wxMpTemplateMessage.addData(new WxMaTemplateMessage.Data("keynote4", orderDto.getOrderStatusEnum().getMessage()));
        wxMpTemplateMessage.addData(new WxMaTemplateMessage.Data("keyword6", PushMessageConstant.END_MESSAGE));
        //异常用try-catch捕获，防止抛出异常造成回滚
//        try {
//            //推送消息
//        }catch (WxErrorException e){
//            log.error("[微信消息推送]推送失败，e={}",e);
//        }
    }
}