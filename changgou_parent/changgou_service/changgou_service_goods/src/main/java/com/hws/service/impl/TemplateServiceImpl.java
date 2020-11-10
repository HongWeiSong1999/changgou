package com.hws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hws.dao.TemplateMapper;
import com.hws.goods.pojo.Template;
import com.hws.service.TemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ClassName:TemplateServiceImpl
 * Package:com.hws.service.impl
 * Description:
 *
 * @ date:2020/11/9 16:42
 * @ author:hws
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * template条件 + 分页查找
     */
    @Override
    public PageInfo<Template> findPage(Template template, Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createTemplate(template);
        return new PageInfo<Template>(templateMapper.selectByExample(example));
    }

    /**
     * 创建example的模板
     */
    public Example createTemplate(Template template) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (template != null) {
            if (!StringUtils.isEmpty(String.valueOf(template.getId()))) {
                criteria.andEqualTo("id", template.getId());
            }
            if (!StringUtils.isEmpty(template.getName())) {
                criteria.andLike("name", "%" + template.getName() + "%");
            }
            if (!StringUtils.isEmpty(String.valueOf(template.getSpecNum()))) {
                criteria.andEqualTo("specNum", template.getSpecNum());
            }
            if (!StringUtils.isEmpty(String.valueOf(template.getParaNum()))) {
                criteria.andEqualTo("paraNum", template.getParaNum());
            }
        }
        return example;
    }

    /**
     * 分页查找
     */
    @Override
    public PageInfo<Template> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Template>(templateMapper.selectAll());
    }

    /**
     * 根据template条件查找
     */
    @Override
    public List<Template> findList(Template template) {
        Example example = createTemplate(template);
        return templateMapper.selectByExample(example);
    }

    /**
     * 根据id进行删除
     */
    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据template条件进行更新
     */
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }

    /**
     * 根据template条件进行新增
     */
    @Override
    public void add(Template template) {
        templateMapper.insertSelective(template);
    }

    /**
     * 根据id进行搜索
     */
    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 搜索所有的数据
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }
}
