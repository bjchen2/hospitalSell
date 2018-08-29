package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.VO.CategoryVO;
import com.wizz.hospitalSell.VO.ProductInfoVO;
import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.domain.ProductCategory;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.enums.ProductSortEnum;
import com.wizz.hospitalSell.service.CategoryService;
import com.wizz.hospitalSell.service.CommentService;
import com.wizz.hospitalSell.service.ProductInfoService;
import com.wizz.hospitalSell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家端商品
 * Created By Cx On 2018/8/3 22:41
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;

    public ProductInfoVO convert(ProductInfo productInfo){
        if (productInfo == null) return null;
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfo,productInfoVO);
        productInfoVO.setProductScore(commentService.findResultByProductId(productInfoVO.getProductId()));
        return productInfoVO;
    }

    public List<ProductInfoVO> convert(List<ProductInfo> productInfos){
        if (productInfos == null) return null;
        return productInfos.stream().map(e -> convert(e)).collect(Collectors.toList());
    }

    @GetMapping("/list")
    public ResultVO list(@RequestParam(defaultValue = "1")Integer sort){
        //获取所有上架商品
        List<ProductInfo> products = productInfoService.findUpAll();
        List<Integer> productTypes = products.stream().map(o -> o.getCategoryType()).collect(Collectors.toList());
        //切记不要for循环一个个找category，这样效率很慢，因为每次查找都要涉及数据库的重新访问
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(productTypes);
        List<CategoryVO> categoryVOs = new ArrayList<>();
        for (ProductCategory pc : categories) {
            //遍历所有已上架的类目
            CategoryVO categoryVO = new CategoryVO(pc.getCategoryName(), pc.getCategoryType());
            List<ProductInfo> temp = new ArrayList<>();
            for (ProductInfo pi : products) {
                //遍历所有商品，若和当前类目相同，则添加进temp
                if (pi.getCategoryType().equals(pc.getCategoryType())) {
                    temp.add(pi);
                }
            }
            //将ProductInfo转换为ProductInfoVO
            List<ProductInfoVO> foods = convert(temp);
            if (sort.equals(ProductSortEnum.PRICE.getCode())){
                //按商品价格的增序排序
                foods.sort(Comparator.comparing(ProductInfoVO::getProductPrice));
            }else {
                //按商品评分的降序排序
                foods.sort((o1, o2) -> o2.getProductScore() - o1.getProductScore());
            }
            categoryVO.setProducts(foods);
            categoryVOs.add(categoryVO);
        }
        return ResultUtil.success(categoryVOs);
    }

    @GetMapping("/key")
    public ResultVO findByKey(String key){
        List<ProductInfoVO> productInfoVOs = convert(productInfoService.findByKey(key));
        return ResultUtil.success(productInfoVOs);
    }
}
