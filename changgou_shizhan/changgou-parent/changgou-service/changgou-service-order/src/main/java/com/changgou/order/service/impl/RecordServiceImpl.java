package com.changgou.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.changgou.order.dao.RecordMapper;
import com.changgou.order.pojo.Record;
import com.changgou.order.service.RecordService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:admin
 * @Description:Record业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;


    /**
     * Record条件+分页查询
     * @param record 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Record> findPage(Record record, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(record);
        //执行搜索
        return new PageInfo<Record>(recordMapper.selectByExample(example));
    }

    /**
     * Record分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Record> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Record>(recordMapper.selectAll());
    }

    /**
     * Record条件查询
     * @param record
     * @return
     */
    @Override
    public List<Record> findList(Record record){
        //构建查询条件
        Example example = createExample(record);
        //根据构建的条件查询数据
        return recordMapper.selectByExample(example);
    }


    /**
     * Record构建查询对象
     * @param record
     * @return
     */
    public Example createExample(Record record){
        Example example=new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        if(record!=null){
            // 
            if(!StringUtils.isEmpty(record.getId())){
                    criteria.andEqualTo("id",record.getId());
            }
            // 
            if(!StringUtils.isEmpty(record.getOrderId())){
                    criteria.andEqualTo("orderId",record.getOrderId());
            }
            // 
            if(!StringUtils.isEmpty(record.getUsername())){
                    criteria.andLike("username","%"+record.getUsername()+"%");
            }
            // 
            if(!StringUtils.isEmpty(record.getIsOvertime())){
                    criteria.andEqualTo("isOvertime",record.getIsOvertime());
            }
            // 
            if(!StringUtils.isEmpty(record.getIsRemind())){
                    criteria.andEqualTo("isRemind",record.getIsRemind());
            }
            // 
            if(!StringUtils.isEmpty(record.getIsDelivery())){
                    criteria.andEqualTo("isDelivery",record.getIsDelivery());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        recordMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Record
     * @param record
     */
    @Override
    public void update(Record record){
        recordMapper.updateByPrimaryKey(record);
    }

    /**
     * 增加Record
     * @param record
     */
    @Override
    public void add(Record record){
        recordMapper.insertSelective(record);
    }

    /**
     * 根据ID查询Record
     * @param id
     * @return
     */
    @Override
    public Record findById(String id){
        return  recordMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Record全部数据
     * @return
     */
    @Override
    public List<Record> findAll() {
        return recordMapper.selectAll();
    }

    /**
     * 将所有已经收款未发货的提醒发货记录表更新为可提醒发货状态
     */
    @Override
    public void updateIsRemind() {
        recordMapper.updateIsRemind();
    }


    /**
     * 提醒发货
     * @param orderId
     * @return
     */
    @Override
    public int remind(String orderId) {
        Record record = new Record();
        record = recordMapper.selectOne(record);
        if (record.getIsOvertime().equals("1") && record.getIsDelivery().equals("0")) {
            record.setIsRemind("0");
            int i = recordMapper.updateByPrimaryKeySelective(record);
            if (i<=0) {
                throw new RuntimeException("提醒失败");
            }

            //...发送消息到mq 让卖家接收

            return i;
        }

        return 0;
    }
}
