package com.hws.goods.dao;
import com.hws.goods.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:hongweisong
 * @Description:Brand的Dao
 *****/
public interface BrandMapper extends Mapper<Brand> {

    List<Brand> findByCategoryId(Integer id);

}
