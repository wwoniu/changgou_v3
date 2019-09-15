package com.changgou.pingjia.controller;
import com.changgou.pingjia.pojo.OrderEvaluation;
import com.changgou.pingjia.service.OrderEvaluationService;
import com.github.pagehelper.PageInfo;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "OrderEvaluationController")
@RestController
@RequestMapping("/orderEvaluation")
@CrossOrigin
public class OrderEvaluationController {

    @Autowired
    private OrderEvaluationService orderEvaluationService;
    @Autowired
    private IdWorker idWorker;

    /***
     * OrderEvaluation分页条件搜索实现
     * @param orderEvaluation
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "OrderEvaluation对象",value = "传入JSON数据",required = false) OrderEvaluation orderEvaluation, @PathVariable  int page, @PathVariable  int size){
        //调用OrderEvaluationService实现分页条件查询OrderEvaluation
        PageInfo<OrderEvaluation> pageInfo = orderEvaluationService.findPage(orderEvaluation, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * OrderEvaluation分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderEvaluationService实现分页查询OrderEvaluation
        PageInfo<OrderEvaluation> pageInfo = orderEvaluationService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param orderEvaluation
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<OrderEvaluation>> findList(@RequestBody(required = false) @ApiParam(name = "OrderEvaluation对象",value = "传入JSON数据",required = false) OrderEvaluation orderEvaluation){
        //调用OrderEvaluationService实现条件查询OrderEvaluation
        List<OrderEvaluation> list = orderEvaluationService.findList(orderEvaluation);
        return new Result<List<OrderEvaluation>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用OrderEvaluationService实现根据主键删除
        orderEvaluationService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改OrderEvaluation数据
     * @param orderEvaluation
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "OrderEvaluation对象",value = "传入JSON数据",required = false) OrderEvaluation orderEvaluation,@PathVariable Long id){
        //设置主键值
        orderEvaluation.setId(id);
        //调用OrderEvaluationService实现修改OrderEvaluation
        orderEvaluationService.update(orderEvaluation);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增OrderEvaluation数据
     * @param orderEvaluation
     * @return
     */
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "OrderEvaluation对象",value = "传入JSON数据",required = true) OrderEvaluation orderEvaluation){
        //调用OrderEvaluationService实现添加OrderEvaluation
        orderEvaluation.setId(Long.valueOf(idWorker.nextId() + ""));

        orderEvaluationService.add(orderEvaluation);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询OrderEvaluation数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<OrderEvaluation> findById(@PathVariable Long id){
        //调用OrderEvaluationService实现根据主键查询OrderEvaluation
        OrderEvaluation orderEvaluation = orderEvaluationService.findById(id);
        return new Result<OrderEvaluation>(true,StatusCode.OK,"查询成功",orderEvaluation);
    }

    /***
     * 查询OrderEvaluation全部数据
     * @return
     */
    @GetMapping
    public Result<List<OrderEvaluation>> findAll(){
        //调用OrderEvaluationService实现查询所有OrderEvaluation
        List<OrderEvaluation> list = orderEvaluationService.findAll();
        return new Result<List<OrderEvaluation>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * 根据skuId查询所有评价
     * @return
     */
    @GetMapping("/find")
    public Result findBySkuId(Long skuId) {
        List<OrderEvaluation> result = orderEvaluationService.findBySkuId(skuId);
        return new Result(true, StatusCode.OK, "成功", result);
    }
}
