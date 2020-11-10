package com.hws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hws.dao.AlbumMapper;
import com.hws.goods.pojo.Album;
import com.hws.service.AlbumService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ClassName:AlbumServiceImpl
 * Package:com.hws.service.impl
 * Description:
 *
 * @ date:2020/11/9 9:39
 * @ author:hws
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 新增
     */
    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     * 根据id进行查找
     */
    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部照片
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * Album条件查询
     */
    @Override
    public List<Album> findList(Album album) {
        //构建查询条件
        Example example = createExample(album);
        //根据构建的条件查询数据
        return albumMapper.selectByExample(example);
    }

    /**
     * Album构建查询对象
     */
    public Example createExample(Album album){
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null){
            //编号
            if (!StringUtils.isEmpty(String.valueOf(album.getId()))){
                criteria.andEqualTo("id",album.getId());
            }
            //相册名称(模糊)
            if (!StringUtils.isEmpty(album.getTitle())){
                criteria.andLike("title","%"+album.getTitle()+"%");
            }
            //相册封面
            if (!StringUtils.isEmpty(album.getImage())){
                criteria.andEqualTo("image",album.getImage());
            }
            //图片列表
            if (!StringUtils.isEmpty(album.getImageItems())){
                criteria.andEqualTo("imageItems",album.getImageItems());
            }
        }
        return example;
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Album>(albumMapper.selectAll());
    }

    /**
     * 分页查询+条件
     */
    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page,size);
        //构造方法
        Example example = createExample(album);
        //执行搜索
        return new PageInfo<Album>(albumMapper.selectByExample(example));
    }
}
