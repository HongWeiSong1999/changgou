package com.hws.goods.service;
import com.hws.goods.pojo.Goods;
import com.hws.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:hongweisong
 * @Description:Spu业务层接口
 *****/
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     *将参数的信息保存到SKU和SPU的表中
     */
    void saveGoods(Goods goods);

    /**
     * 根据SPU的id查询Goods的数据
     */
    Goods findGoodsById(Long id);

    /**
     * 实现商品的审核
     */
    void audit(Long spuid);

    /**
     * 实现商品的下架
     */
    void pull(Long spuid);

    /**
     * 实现商品的上架  （没删除并且审核过才可以上架）
     */
    void push(Long spuid);

    /**
     * 实现商品的批量上架  （没删除并且审核过才可以上架）
     */
    void pushMany(Long[] ids);
}
