package com.changgou.content.feign;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="user")
@RequestMapping("/orderEvaluation")
public interface OrderEvaluationFeign {

    /***
     * OrderEvaluation分页条件搜索实现
     * @param orderEvaluation
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) OrderEvaluation orderEvaluation, @PathVariable  int page, @PathVariable  int size);

    /***
     * OrderEvaluation分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size);

    /***
     * 多条件搜索品牌数据
     * @param orderEvaluation
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<OrderEvaluation>> findList(@RequestBody(required = false) OrderEvaluation orderEvaluation);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改OrderEvaluation数据
     * @param orderEvaluation
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody OrderEvaluation orderEvaluation,@PathVariable Integer id);

    /***
     * 新增OrderEvaluation数据
     * @param orderEvaluation
     * @return
     */
    @PostMapping
    Result add(@RequestBody OrderEvaluation orderEvaluation);

    /***
     * 根据ID查询OrderEvaluation数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<OrderEvaluation> findById(@PathVariable Integer id);

    /***
     * 查询OrderEvaluation全部数据
     * @return
     */
    @GetMapping
    Result<List<OrderEvaluation>> findAll();
}