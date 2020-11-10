package com.hws.controller;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Template;
import com.hws.service.TemplateService;
import entity.MessageConstant;
import entity.Result;
import entity.StatusCode;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:TemplateController
 * Package:com.hws.controller
 * Description:
 *
 * @ date:2020/11/9 17:27
 * @ author:hws
 */
@RestController
@CrossOrigin
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Template template,
                                     @PathVariable(value = "page") Integer page,
                                     @PathVariable(value = "size") Integer size){
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result(true, StatusCode.OK, MessageConstant.QUERY_TEMPLATE_SUCCESS,pageInfo);
    }

    /***
     * Template分页查询
     * @param page 当前页
     * @param size 每页显示多少条记录
     * @return
     */
    @GetMapping("/findPage/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page,
                                     @PathVariable(value = "size") Integer size){
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        return new Result(true, StatusCode.OK, MessageConstant.QUERY_TEMPLATE_SUCCESS,pageInfo);
    }

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    @PostMapping("/findList")
    public Result<List<Template>> findList(@RequestBody Template template){
        List<Template> templateList = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, MessageConstant.QUERY_TEMPLATE_SUCCESS,templateList);
    }

    /***
     * 删除Template
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(value = "id") Integer id){
        templateService.delete(id);
        return new Result(true,StatusCode.OK,MessageConstant.DELETE_TEMPLATE_SUCCESS);
    }


    /***
     * 修改Template数据
     * @param template
     * @param id
     */
    @PutMapping("/update/{id}")
    public Result update(@RequestBody Template template,
                         @PathVariable(value = "id") Integer id){
        templateService.update(template);
        return new Result(true,StatusCode.OK,MessageConstant.EDIT_TEMPLATE_SUCCESS);
    }


    /***
     * 新增Template
     * @param template
     */
    @PostMapping("/add")
    public Result add(@RequestBody Template template){
        templateService.add(template);
        return new Result(true,StatusCode.OK,MessageConstant.EDIT_TEMPLATE_SUCCESS);
    }


    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable(value = "id") Integer id){
        Template template = templateService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.QUERY_TEMPLATE_SUCCESS,template);
    }


    /***
     * 查询所有Template
     * @return
     */
    @GetMapping("/findAll")
    public Result<List<Template>> findAll(){
        List<Template> templateList = templateService.findAll();
        return new Result<List<Template>>(true,StatusCode.OK,MessageConstant.QUERY_TEMPLATE_SUCCESS,templateList);
    }

}
