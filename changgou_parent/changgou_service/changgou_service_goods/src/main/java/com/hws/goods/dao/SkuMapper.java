package com.hws.goods.dao;
import com.hws.goods.pojo.Sku;
import io.swagger.models.auth.In;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:hongweisong
 * @Description:Sku的Dao
 *****/
public interface SkuMapper extends Mapper<Sku> {

    List<Sku> listSkuBySpuId(Long spuId);

}
