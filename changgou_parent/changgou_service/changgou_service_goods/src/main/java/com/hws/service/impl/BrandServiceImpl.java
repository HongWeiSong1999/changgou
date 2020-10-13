package com.hws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hws.dao.BrandMapper;
import com.hws.goods.pojo.Brand;
import com.hws.service.BrandService;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * ClassName:BrandServiceImpl
 * Package:com.hws.service.impl
 * Description:
 *
 * @ date:2020/10/12 16:13
 * @ author:hws
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findList(Brand brand) {
        //自定义条件搜索对象 Example
        Example example = new Example(Brand.class);
        //条件构造器
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            //相当于 根据名字模糊搜索 where name like '%华%'
            if (!StringUtil.isEmpty(brand.getName())) {
                /**
                 * 1:Brand（javaBean）的属性名
                 * 2:占位符参数，搜索的条件
                 */
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //相当于 根据首字母搜索 where letter = 'H'
            if (!StringUtil.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        //使用分页插件实现分页，后面紧跟集合
        PageHelper.startPage(page, size);
        //调用selectAll，查询所有的集合
        List<Brand> brandList = brandMapper.selectAll();
        return new PageInfo<Brand>(brandList);
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //使用分页插件进行分页查询
        PageHelper.startPage(page, size);
        //根据传过来的参数进行查询
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (!StringUtil.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtil.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        List<Brand> brandList = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brandList);
    }
}
