package com.changgou.usercenter.controller;

import com.changgou.usercenter.config.TokenDecode;
import com.changgou.usercenter.pojo.Footprint;
import com.changgou.usercenter.service.FootprintService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "FootprintController")
@RestController
@RequestMapping("/footprint")
@CrossOrigin
public class FootprintController {
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private FootprintService footprintService;

    /***
     * Footprint分页条件搜索实现
     * @param footprint
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Footprint对象",value = "传入JSON数据",required = false) Footprint footprint, @PathVariable  int page, @PathVariable  int size){
        //调用FootprintService实现分页条件查询Footprint
        PageInfo<Footprint> pageInfo = footprintService.findPage(footprint, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Footprint分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用FootprintService实现分页查询Footprint
        PageInfo<Footprint> pageInfo = footprintService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param footprint
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Footprint>> findList(@RequestBody(required = false) @ApiParam(name = "Footprint对象",value = "传入JSON数据",required = false) Footprint footprint){
        //调用FootprintService实现条件查询Footprint
        List<Footprint> list = footprintService.findList(footprint);
        return new Result<List<Footprint>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用FootprintService实现根据主键删除
        footprintService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Footprint数据
     * @param footprint
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Footprint对象",value = "传入JSON数据",required = false) Footprint footprint,@PathVariable Long id){
        //设置主键值
        footprint.setId(id);
        //调用FootprintService实现修改Footprint
        footprintService.update(footprint);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Footprint数据
     * @param footprint
     * @return
     */
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Footprint对象",value = "传入JSON数据",required = true) Footprint footprint){
        //调用FootprintService实现添加Footprint
        footprintService.add(footprint);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Footprint数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Footprint> findById(@PathVariable Integer id){
        //调用FootprintService实现根据主键查询Footprint
        Footprint footprint = footprintService.findById(id);
        return new Result<Footprint>(true,StatusCode.OK,"查询成功",footprint);
    }

    /***
     * 查询Footprint全部数据
     * @return
     */
    @GetMapping
    public Result<List<Footprint>> findAll(){
        //调用FootprintService实现查询所有Footprint
        List<Footprint> list = footprintService.findAll();
        return new Result<List<Footprint>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * 根据skuid添加足迹
     * @param skuId
     * @return
     */
    @PostMapping("/addSkuId")
    public Result addBySkuId(Long skuId) {
        String username = tokenDecode.getUserInfo().get("username");
        footprintService.addBySkuId(skuId,username);
        return new Result(true,StatusCode.OK,"success");
    }
}
