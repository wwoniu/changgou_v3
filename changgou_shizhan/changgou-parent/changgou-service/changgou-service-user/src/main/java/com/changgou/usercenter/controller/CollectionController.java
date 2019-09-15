package com.changgou.usercenter.controller;

import com.changgou.usercenter.config.TokenDecode;
import com.changgou.usercenter.pojo.Collection;
import com.changgou.usercenter.service.CollectionService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "CollectionController")
@RestController
@RequestMapping("/collection")
@CrossOrigin
public class CollectionController {
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private CollectionService collectionService;

    /***
     * Collection分页条件搜索实现
     * @param collection
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Collection对象",value = "传入JSON数据",required = false) Collection collection, @PathVariable  int page, @PathVariable  int size){
        //调用CollectionService实现分页条件查询Collection
        PageInfo<Collection> pageInfo = collectionService.findPage(collection, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Collection分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CollectionService实现分页查询Collection
        PageInfo<Collection> pageInfo = collectionService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param collection
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Collection>> findList(@RequestBody(required = false) @ApiParam(name = "Collection对象",value = "传入JSON数据",required = false) Collection collection){
        //调用CollectionService实现条件查询Collection
        List<Collection> list = collectionService.findList(collection);
        return new Result<List<Collection>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用CollectionService实现根据主键删除
        collectionService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Collection数据
     * @param collection
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Collection对象",value = "传入JSON数据",required = false) Collection collection,@PathVariable Long id){
        //设置主键值
        collection.setId(id);
        //调用CollectionService实现修改Collection
        collectionService.update(collection);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Collection数据
     * @param collection
     * @return
     */
    @PostMapping
    public Result add(@RequestBody(required = false)  @ApiParam(name = "Collection对象",value = "传入JSON数据",required = false) Collection collection,Long skuId){
        //调用CollectionService实现添加Collection
        String username = tokenDecode.getUserInfo().get("username");
        collection.setUsername(username);
        collectionService.add(collection);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Collection数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Collection> findById(@PathVariable Integer id){
        //调用CollectionService实现根据主键查询Collection
        Collection collection = collectionService.findById(id);
        return new Result<Collection>(true,StatusCode.OK,"查询成功",collection);
    }

    /***
     * 查询Collection全部数据
     * @return
     */
    @GetMapping
    public Result<List<Collection>> findAll(){
        //调用CollectionService实现查询所有Collection
        List<Collection> list = collectionService.findAll();
        return new Result<List<Collection>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * 根据skuid添加收藏
     * @param skuId
     * @return
     */
    @PostMapping("/addSkuId")
    public Result addBySkuId(Long skuId) {
        String username = tokenDecode.getUserInfo().get("username");
        collectionService.addBySkuId(skuId,username);
        return new Result(true,StatusCode.OK,"success");
    }
}
