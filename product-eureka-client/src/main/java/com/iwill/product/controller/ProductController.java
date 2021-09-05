package com.iwill.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping("purchase/{userId}/{productId}/{amount}")
    public String purchaseProduct(@PathVariable("userId")long userId ,
                                  @PathVariable("productId")long productId ,
                                  @PathVariable("amount")long amount ,
                                  HttpServletRequest request){
        System.out.println("扣减产品余额");
        String url = "http://FUND-EUREKA-CLIENT/account/account/balance/{userId}/{amount}";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        params.put("amount",amount);
        String response = restTemplate.postForObject(url,null,String.class,params);
        System.out.println("请求fund扣款结果:"+response);
        System.out.println("记录交易信息");
        return "success";
    }
}
