package com.hws.service;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Album;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * ClassName:AlbumService
 * Package:com.hws.service
 * Description:
 *
 * @ date:2020/11/8 17:42
 * @ author:hws
 */
public interface AlbumService {

    /**
     * 新增照片
     */
    void add(Album album);

    /**
     * 删除照片
     */
    void delete(Long id);

    /**
     * 修改照片
     */
    void update(Album album);

    /**
     * 根据ID查询照片
     */
    Album findById(Long id);

    /**
     * 查询所有照片
     */
    List<Album> findAll();

    /**
     * Album多条件搜索方法
     */
    List<Album> findList(Album album);

    /**
     * 照片分页查询
     */
    PageInfo<Album> findPage(Integer page,Integer size);

    /**
     * 照片多条件查询
     */
    PageInfo<Album> findPage(Album album,Integer page, Integer size);

    

}
