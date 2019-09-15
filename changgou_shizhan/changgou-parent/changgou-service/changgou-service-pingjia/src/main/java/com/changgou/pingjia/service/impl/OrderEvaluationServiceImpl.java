package com.changgou.pingjia.service.impl;
import com.changgou.pingjia.dao.OrderEvaluationMapper;
import com.changgou.pingjia.pojo.OrderEvaluation;
import com.changgou.pingjia.service.OrderEvaluationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class OrderEvaluationServiceImpl implements OrderEvaluationService {

    @Autowired
    private OrderEvaluationMapper orderEvaluationMapper;


    /**
     * OrderEvaluation条件+分页查询
     * @param orderEvaluation 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<OrderEvaluation> findPage(OrderEvaluation orderEvaluation, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(orderEvaluation);
        //执行搜索
        return new PageInfo<OrderEvaluation>(orderEvaluationMapper.selectByExample(example));
    }

    /**
     * OrderEvaluation分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<OrderEvaluation> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<OrderEvaluation>(orderEvaluationMapper.selectAll());
    }

    /**
     * OrderEvaluation条件查询
     * @param orderEvaluation
     * @return
     */
    @Override
    public List<OrderEvaluation> findList(OrderEvaluation orderEvaluation){
        //构建查询条件
        Example example = createExample(orderEvaluation);
        //根据构建的条件查询数据
        return orderEvaluationMapper.selectByExample(example);
    }


    /**
     * OrderEvaluation构建查询对象
     * @param orderEvaluation
     * @return
     */
    public Example createExample(OrderEvaluation orderEvaluation){
        Example example=new Example(OrderEvaluation.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderEvaluation!=null){
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getId())){
                    criteria.andEqualTo("id",orderEvaluation.getId());
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getUsername())){
                    criteria.andLike("username","%"+orderEvaluation.getUsername()+"%");
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getOrderId())){
                    criteria.andEqualTo("orderId",orderEvaluation.getOrderId());
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getScorce())){
                    criteria.andEqualTo("scorce",orderEvaluation.getScorce());
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getDetails())){
                    criteria.andEqualTo("details",orderEvaluation.getDetails());
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getUserImage())){
                    criteria.andEqualTo("userImage",orderEvaluation.getUserImage());
            }
            // 
            if(!StringUtils.isEmpty(orderEvaluation.getSkuId())){
                    criteria.andEqualTo("skuId",orderEvaluation.getSkuId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        orderEvaluationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改OrderEvaluation
     * @param orderEvaluation
     */
    @Override
    public void update(OrderEvaluation orderEvaluation){
        orderEvaluationMapper.updateByPrimaryKey(orderEvaluation);
    }

    /**
     * 增加OrderEvaluation
     * @param orderEvaluation
     */
    @Override
    public void add(OrderEvaluation orderEvaluation){
        orderEvaluationMapper.insert(orderEvaluation);
    }

    /**
     * 根据ID查询OrderEvaluation
     * @param id
     * @return
     */
    @Override
    public OrderEvaluation findById(Long id){
        return  orderEvaluationMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询OrderEvaluation全部数据
     * @return
     */
    @Override
    public List<OrderEvaluation> findAll() {
        return orderEvaluationMapper.selectAll();
    }
    /**
     * 根据skuid查询商品所有评价
     * @return
     * @param skuId
     */
    @Override
    public List<OrderEvaluation> findBySkuId(Long skuId) {
        OrderEvaluation orderEvaluation = new OrderEvaluation();
        orderEvaluation.setSkuId(skuId);
        return orderEvaluationMapper.select(orderEvaluation);
    }
}
