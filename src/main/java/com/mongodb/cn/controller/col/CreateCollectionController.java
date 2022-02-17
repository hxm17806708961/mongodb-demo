package com.mongodb.cn.controller.col;

import com.mongodb.cn.service.col.CreateCollectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hxmao
 * @date 2022/2/17 10:10
 */
@RestController
public class CreateCollectionController {

    @Resource
    private CreateCollectionService createCollectionService;

    @GetMapping("/create")
    public Object create(){

        return createCollectionService.createCollection();
    }
}
