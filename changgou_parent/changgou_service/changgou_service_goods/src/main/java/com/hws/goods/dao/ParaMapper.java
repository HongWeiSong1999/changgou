package com.hws.goods.dao;
import com.hws.goods.pojo.Para;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:hongweisong
 * @Description:Para的Dao
 *****/
public interface ParaMapper extends Mapper<Para> {

    List<Para> findByCategoryId(Integer id);
}
