package com.mongodb.cn.dao;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author hxmao
 * @date 2022/2/17 10:04
 */
@Data
@ToString
@Accessors(chain = true)
public class Status {

    private Integer weight;
    private Integer height;

}
