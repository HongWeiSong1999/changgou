package com.hws.service;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Template;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * ClassName:TemplateService
 * Package:com.hws.service
 * Description:
 *
 * @ date:2020/11/9 16:42
 * @ author:hws
 */
public interface TemplateService {
    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Template template, Integer page, Integer size);

    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Integer page,Integer size);

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 删除Template
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Template数据
     * @param template
     */
    void update(Template template);

    /***
     * 新增Template
     * @param template
     */
    void add(Template template);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    Template findById(Integer id);

    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();
}
