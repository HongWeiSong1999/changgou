package com.hws.goods.dao;
import com.hws.goods.pojo.Template;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:hongweisong
 * @Description:Template的Dao
 *****/
public interface TemplateMapper extends Mapper<Template> {

    Template findByCategoryId(Integer id);

}
