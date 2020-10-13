package com.hws.service;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Brand;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * ClassName:BrandService
 * Package:com.hws.service
 * Description:
 *
 * @ date:2020/10/12 16:13
 * @ author:hws
 */
public interface BrandService {

    List<Brand> findAll();

    Brand findById(Integer id);

    void insert(Brand brand);

    void update(Brand brand);

    void delete(Integer id);

    List<Brand> findList(Brand brand);

    PageInfo<Brand> findPage(Integer page, Integer size);

    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

}
