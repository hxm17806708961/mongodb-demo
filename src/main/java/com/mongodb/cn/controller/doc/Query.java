package com.mongodb.cn.controller.doc;

import com.mongodb.cn.service.doc.QueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hxmao
 * @date 2022/2/17 10:24
 */
@RestController
public class Query {

    @Resource
    private QueryService service;

    @GetMapping("/query")
    private Object query(){
        return service.findAll();
    }
}
