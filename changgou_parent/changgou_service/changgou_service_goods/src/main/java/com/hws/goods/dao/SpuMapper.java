package com.hws.goods.dao;
import com.hws.goods.pojo.Sku;
import com.hws.goods.pojo.Spu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:hongweisong
 * @Description:Spu的Dao
 *****/
public interface SpuMapper extends Mapper<Spu> {
    List<Sku> selectSkuListById(Long id);
}
