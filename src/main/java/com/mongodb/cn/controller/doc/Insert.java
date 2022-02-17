package com.mongodb.cn.controller.doc;

import com.mongodb.cn.service.doc.InsertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hxmao
 * @date 2022/2/17 10:20
 */
@RestController
public class Insert {

    @Resource
    private InsertService service;

    @GetMapping("/insert")
    public Object insert(){
        return service.insert();
    }

    @GetMapping("/insertAll")
    public Object insertAll(){
        return service.insertMany();
    }
}
