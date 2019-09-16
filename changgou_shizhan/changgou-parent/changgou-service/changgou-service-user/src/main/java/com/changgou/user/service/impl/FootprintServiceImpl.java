package com.changgou.user.service.impl;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.user.dao.FootprintMapper;
import com.changgou.user.pojo.Footprint;
import com.changgou.user.service.FootprintService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private FootprintMapper footprintMapper;
    @Autowired
    private IdWorker idWorker;

    /**
     * Footprint条件+分页查询
     * @param footprint 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Footprint> findPage(Footprint footprint, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(footprint);
        //执行搜索
        return new PageInfo(footprintMapper.selectByExample(example));
    }

    /**
     * Footprint分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Footprint> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo(footprintMapper.selectAll());
    }

    /**
     * Footprint条件查询
     * @param footprint
     * @return
     */
    @Override
    public List findList(Footprint footprint){
        //构建查询条件
        Example example = createExample(footprint);
        //根据构建的条件查询数据
        return footprintMapper.selectByExample(example);
    }


    /**
     * Footprint构建查询对象
     * @param footprint
     * @return
     */
    public Example createExample(Footprint footprint){
        Example example=new Example(Footprint.class);
        Example.Criteria criteria = example.createCriteria();
        if(footprint!=null){
            // 
            if(!StringUtils.isEmpty(footprint.getUsername())){
                    criteria.andLike("username","%"+footprint.getUsername()+"%");
            }
            // 
            if(!StringUtils.isEmpty(footprint.getSkuImage())){
                    criteria.andEqualTo("skuImage",footprint.getSkuImage());
            }
            // 
            if(!StringUtils.isEmpty(footprint.getSkuName())){
                    criteria.andEqualTo("skuName",footprint.getSkuName());
            }
            // 
            if(!StringUtils.isEmpty(footprint.getSkuPrice())){
                    criteria.andEqualTo("skuPrice",footprint.getSkuPrice());
            }
            // 
            if(!StringUtils.isEmpty(footprint.getCommentNum())){
                    criteria.andEqualTo("commentNum",footprint.getCommentNum());
            }
            // 
            if(!StringUtils.isEmpty(footprint.getSkuId())){
                    criteria.andEqualTo("skuId",footprint.getSkuId());
            }
            // 
            if(!StringUtils.isEmpty(footprint.getId())){
                    criteria.andEqualTo("id",footprint.getId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        footprintMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Footprint
     * @param footprint
     */
    @Override
    public void update(Footprint footprint){
        footprintMapper.updateByPrimaryKey(footprint);
    }

    /**
     * 增加Footprint
     * @param footprint
     */
    @Override
    public void add(Footprint footprint){
        footprintMapper.insert(footprint);
    }

    /**
     * 根据ID查询Footprint
     * @param id
     * @return
     */
    @Override
    public Footprint findById(Integer id){
        return footprintMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Footprint全部数据
     * @return
     */
    @Override
    public List findAll() {
        return footprintMapper.selectAll();
    }

    /**
     * 根据skuid添加足迹
     * @param skuId
     * @return
     */
    @Override
    public void addBySkuId(Long skuId,String username) {
        Sku sku = skuFeign.findById(skuId).getData();
        Footprint footprint = new Footprint();
        footprint.setSkuId(skuId);
        footprint.setSkuImage(sku.getImage());
        footprint.setSkuName(sku.getName());
        footprint.setSkuPrice(sku.getPrice());
        footprint.setUsername(username);
        footprint.setId(idWorker.nextId());
        footprintMapper.insert(footprint);
    }
}
