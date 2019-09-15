package com.changgou.pingjia.service;
import com.changgou.pingjia.pojo.OrderEvaluation;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface OrderEvaluationService {

    /***
     * OrderEvaluation多条件分页查询
     * @param orderEvaluation
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderEvaluation> findPage(OrderEvaluation orderEvaluation, int page, int size);

    /***
     * OrderEvaluation分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderEvaluation> findPage(int page, int size);

    /***
     * OrderEvaluation多条件搜索方法
     * @param orderEvaluation
     * @return
     */
    List<OrderEvaluation> findList(OrderEvaluation orderEvaluation);

    /***
     * 删除OrderEvaluation
     * @param id
     */
    void delete(Long id);

    /***
     * 修改OrderEvaluation数据
     * @param orderEvaluation
     */
    void update(OrderEvaluation orderEvaluation);

    /***
     * 新增OrderEvaluation
     * @param orderEvaluation
     */
    void add(OrderEvaluation orderEvaluation);

    /**
     * 根据ID查询OrderEvaluation
     * @param id
     * @return
     */
     OrderEvaluation findById(Long id);

    /***
     * 查询所有OrderEvaluation
     * @return
     */
    List<OrderEvaluation> findAll();
    /**
     * 根据skuid查询商品所有评价
     * @return
     * @param skuId
     */
    List<OrderEvaluation> findBySkuId(Long skuId);
}
