package com.changgou.user.service;



import com.changgou.user.pojo.Footprint;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FootprintService {

    /***
     * Footprint多条件分页查询
     * @param footprint
     * @param page
     * @param size
     * @return
     */
    PageInfo<Footprint> findPage(Footprint footprint, int page, int size);

    /***
     * Footprint分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Footprint> findPage(int page, int size);

    /***
     * Footprint多条件搜索方法
     * @param footprint
     * @return
     */
    List<Footprint> findList(Footprint footprint);

    /***
     * 删除Footprint
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Footprint数据
     * @param footprint
     */
    void update(Footprint footprint);

    /***
     * 新增Footprint
     * @param footprint
     */
    void add(Footprint footprint);

    /**
     * 根据ID查询Footprint
     * @param id
     * @return
     */
     Footprint findById(Integer id);

    /***
     * 查询所有Footprint
     * @return
     */
    List<Footprint> findAll();

    /**
     * 根据skuid添加足迹
     * @param skuId
     * @return
     */
    void addBySkuId(Long skuId, String username);
}
