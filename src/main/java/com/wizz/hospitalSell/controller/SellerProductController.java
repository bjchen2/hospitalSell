package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.domain.ProductCategory;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.ProductCommentDto;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.form.ProductForm;
import com.wizz.hospitalSell.service.CategoryService;
import com.wizz.hospitalSell.service.CommentService;
import com.wizz.hospitalSell.service.ProductInfoService;
import com.wizz.hospitalSell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家商品有关
 * Created By Cx On 2018/7/26 12:08
 */
@RequestMapping("/seller/product")
@Slf4j
@Controller
public class SellerProductController {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;

    /**
     * 商品列表页面
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> m = new HashMap<>();
        //通过更新时间排序分页
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Page<ProductInfo> productInfoPage;
        if (!StringUtils.isEmpty(productName)) {
            //如果商品名不为空则为查询某个商品评价
            List<ProductInfo> productInfos = productInfoService.findByKey(productName);
            if (productInfos == null) productInfos = new ArrayList<>();
            productInfoPage = new PageImpl<>(productInfos.subList((page - 1) * size, page * size >= productInfos.size() ? productInfos.size() : page * size),
                    PageRequest.of(page - 1, size, sort), productInfos.size());
            m.put("key", productName);
        } else {
            productInfoPage = productInfoService.findAll(PageRequest.of(page - 1, size, sort));
        }
        m.put("productInfoPage", productInfoPage);
        m.put("currentPage", page);
        m.put("size", size);
        return new ModelAndView("/product/list", m);
    }

    /**
     * 商品上架
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(String productId) {
        Map<String, Object> m = new HashMap<>();
        m.put("url", "/seller/product/list");
        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            log.error("[商品上架]上架失败");
            m.put("msg", e.getMessage());
            return new ModelAndView("/common/error", m);
        }
        return new ModelAndView("/common/success", m);
    }

    /**
     * 商品下架
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(String productId) {
        Map<String, Object> m = new HashMap<>();
        m.put("url", "/seller/product/list");
        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            log.error("[商品下架]下架失败");
            m.put("msg", e.getMessage());
            return new ModelAndView("/common/error", m);
        }
        return new ModelAndView("/common/success", m);
    }

    /**
     * 新增/修改商品页面
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) String productId) {
        Map<String, Object> m = new HashMap<>();
        if (!StringUtils.isEmpty(productId)) {
            //如果Id不为空则为修改商品操作
            ProductInfo productInfo = productInfoService.findOne(productId);
            m.put("productInfo", productInfo);
        }
        List<ProductCategory> categories = categoryService.findAll();
        m.put("categories", categories);
        return new ModelAndView("/product/index", m);
    }

    /**
     * 保存/修改操作
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult) {
        Map<String, Object> m = new HashMap<>();
        m.put("url", "/seller/product/list");
        if (bindingResult.hasErrors()) {
            //如果参数校验失败
            log.error("【保存/修改商品】操作参数不正确，productForm={}", productForm);
            m.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", m);
        }

        try {
            ProductInfo productInfo = new ProductInfo();
            if (StringUtils.isEmpty(productForm.getProductId())) {
                //如果id为空/不存在，则说明是新增操作
                productForm.setProductId(KeyUtil.genUniqueKey());
            } else {
                //否则为修改操作
                productInfo = productInfoService.findOne(productForm.getProductId());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            log.error("【保存/修改商品】访问数据库失败");
            m.put("msg", e.getMessage());
            return new ModelAndView("common/error", m);
        }
        return new ModelAndView("common/success", m);
    }

    /**
     * 商品评价页面
     */
    @GetMapping("/comment")
    public ModelAndView comment(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> m = new HashMap<>();
        List<ProductCommentDto> productComments;
        if (!StringUtils.isEmpty(productName)) {
            //如果商品名不为空则为查询某个商品评价
            productComments = commentService.findDtosByProductName(productName);
            m.put("key", productName);
        } else {
            productComments = commentService.findAllDtos();
        }
        if (productComments == null) productComments = new ArrayList<>();
        Page<ProductCommentDto> productCommentDtoPage = new PageImpl<>(productComments.subList((page - 1) * size, page * size >= productComments.size() ? productComments.size() : page * size),
                PageRequest.of(page - 1, size), productComments.size());
        m.put("productCommentDtoPage", productCommentDtoPage);
        m.put("currentPage", page);
        m.put("size", size);
        return new ModelAndView("/product/comment", m);
    }

}
