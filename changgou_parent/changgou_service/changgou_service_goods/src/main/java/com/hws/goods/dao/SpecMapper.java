package com.hws.goods.dao;
import com.hws.goods.pojo.Spec;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:hongweisong
 * @Description:Spec的Dao
 *****/
public interface SpecMapper extends Mapper<Spec> {

    List<Spec> findByCategoryId(Integer id);

}
