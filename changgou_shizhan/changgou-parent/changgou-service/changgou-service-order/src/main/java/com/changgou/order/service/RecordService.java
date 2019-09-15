package com.changgou.order.service;

import com.github.pagehelper.PageInfo;
import com.changgou.order.pojo.Record;

import java.util.List;

/****
 * @Author:admin
 * @Description:Record业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface RecordService {

    /***
     * Record多条件分页查询
     * @param record
     * @param page
     * @param size
     * @return
     */
    PageInfo<Record> findPage(Record record, int page, int size);

    /***
     * Record分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Record> findPage(int page, int size);

    /***
     * Record多条件搜索方法
     * @param record
     * @return
     */
    List<Record> findList(Record record);

    /***
     * 删除Record
     * @param id
     */
    void delete(String id);

    /***
     * 修改Record数据
     * @param record
     */
    void update(Record record);

    /***
     * 新增Record
     * @param record
     */
    void add(Record record);

    /**
     * 根据ID查询Record
     * @param id
     * @return
     */
     Record findById(String id);

    /***
     * 查询所有Record
     * @return
     */
    List<Record> findAll();

    void updateIsRemind();

    int remind(String orderId);
}
