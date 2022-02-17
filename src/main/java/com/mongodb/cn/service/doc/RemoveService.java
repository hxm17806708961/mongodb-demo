package com.mongodb.cn.service.doc;

/**
 * @author hxmao
 * @date 2022/2/17 10:18
 */

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.cn.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RemoveService {

    /**
     * 设置集合名称
     */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 删除集合中【符合条件】的【一个]或[多个】文档
     *
     * @return 删除用户信息的结果
     */
    public Object remove() {
        // 设置查询条件参数
        int age = 30;
        String sex = "男";
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age).and("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档信息
        DeleteResult result = mongoTemplate.remove(query, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除 " + result.getDeletedCount() + " 条文档信息";
        log.info(resultInfo);
        return resultInfo;
    }

    /**
     * 删除【符合条件】的【单个文档】，并返回删除的文档。
     *
     * @return 删除的用户信息
     */
    public Object findAndRemove() {
        // 设置查询条件参数
        String name = "zhangsansan";
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is(name);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的第一条文档,并返回删除的文档信息
        User result = mongoTemplate.findAndRemove(query, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + result;
        log.info(resultInfo);
        return result;
    }

    /**
     * 删除【符合条件】的【全部文档】，并返回删除的文档。
     *
     * @return 删除的全部用户信息
     */
    public Object findAllAndRemove() {
        // 设置查询条件参数
        int age = 22;
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档,并返回删除的全部文档信息
        List<User> resultList = mongoTemplate.findAllAndRemove(query, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + resultList;
        log.info(resultInfo);
        return resultList;
    }

    @Slf4j
    @Service
    public static class UpdateService {

        /**
         * 设置集合名称
         */
        private static final String COLLECTION_NAME = "users";

        @Resource
        private MongoTemplate mongoTemplate;

        /**
         * 更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】
         *
         * @return 执行更新的结果
         */
        public Object update() {
            // 创建条件对象
            Criteria criteria = Criteria.where("age").is(30);
            // 创建查询对象，然后将条件对象添加到其中
            Query query = new Query(criteria);
            // 创建更新对象,并设置更新的内容
            Update update = new Update().set("age", 33).set("name", "zhangsansan");
            // 执行更新，如果没有找到匹配查询的文档，则创建并插入一个新文档
            UpdateResult result = mongoTemplate.upsert(query, update, User.class, COLLECTION_NAME);
            // 输出结果信息
            String resultInfo = "匹配到" + result.getMatchedCount() + "条数据,对第一条数据进行了更改";
            log.info("更新结果：{}", resultInfo);
            return resultInfo;
        }

        /**
         * 更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】
         *
         * @return 执行更新的结果
         */
        public Object updateFirst() {
            // 创建条件对象
            Criteria criteria = Criteria.where("name").is("zhangsan");
            // 创建查询对象，然后将条件对象添加到其中，并设置排序
            Query query = new Query(criteria).with(Sort.by("age").ascending());
            // 创建更新对象,并设置更新的内容
            Update update = new Update().set("age", 30).set("name", "zhangsansan");
            // 执行更新
            UpdateResult result = mongoTemplate.updateFirst(query, update, User.class, COLLECTION_NAME);
            // 输出结果信息
            String resultInfo = "共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
            log.info("更新结果：{}", resultInfo);
            return resultInfo;
        }

        /**
         * 更新【匹配查询】到的【文档数据集合】中的【所有数据】
         *
         * @return 执行更新的结果
         */
        public Object updateMany() {
            // 创建条件对象
            Criteria criteria = Criteria.where("age").gt(28);
            // 创建查询对象，然后将条件对象添加到其中
            Query query = new Query(criteria);
            // 设置更新字段和更新的内容
            Update update = new Update().set("age", 29).set("salary", "1999");
            // 执行更新
            UpdateResult result = mongoTemplate.updateMulti(query, update, User.class, COLLECTION_NAME);
            // 输出结果信息
            String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
            log.info("更新结果：{}", resultInfo);
            return resultInfo;
        }

    }
}
