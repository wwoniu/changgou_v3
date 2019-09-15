package com.changgou.order.controller;

import com.changgou.order.pojo.Record;
import com.github.pagehelper.PageInfo;
import com.changgou.order.service.RecordService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    @Autowired
    private RecordService recordService;

    /***
     * Record分页条件搜索实现
     * @param record
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Record record, @PathVariable  int page, @PathVariable  int size){
        //调用RecordService实现分页条件查询Record
        PageInfo<Record> pageInfo = recordService.findPage(record, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Record分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RecordService实现分页查询Record
        PageInfo<Record> pageInfo = recordService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param record
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Record>> findList(@RequestBody(required = false)  Record record){
        //调用RecordService实现条件查询Record
        List<Record> list = recordService.findList(record);
        return new Result<List<Record>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用RecordService实现根据主键删除
        recordService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Record数据
     * @param record
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Record record,@PathVariable String id){
        //设置主键值
        record.setId(id);
        //调用RecordService实现修改Record
        recordService.update(record);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Record数据
     * @param record
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Record record){
        //调用RecordService实现添加Record
        recordService.add(record);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Record数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Record> findById(@PathVariable String id){
        //调用RecordService实现根据主键查询Record
        Record record = recordService.findById(id);
        return new Result<Record>(true,StatusCode.OK,"查询成功",record);
    }

    /***
     * 查询Record全部数据
     * @return
     */
    @GetMapping
    public Result<List<Record>> findAll(){
        //调用RecordService实现查询所有Record
        List<Record> list = recordService.findAll();
        return new Result<List<Record>>(true, StatusCode.OK,"查询成功",list) ;
    }


    /**
     * 提醒发货
     * @param orderId
     * @return
     */
    @RequestMapping("/remind")
    public Result remind(@RequestParam String orderId){
        int i = recordService.remind(orderId);
        return new Result(true, StatusCode.OK,"提醒发货成功",i);
    }
}
