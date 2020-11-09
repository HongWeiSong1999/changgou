package com.hws.controller;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Album;
import com.hws.service.AlbumService;
import entity.MessageConstant;
import entity.Result;
import entity.StatusCode;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:AlbumController
 * Package:com.hws.controller
 * Description:
 *
 * @ date:2020/11/9 13:45
 * @ author:hws
 */
@RestController
@CrossOrigin
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /***
     * Album分页条件搜索实现 @RequestBody(required = false)
     * @param album
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage/{page}/{size}")
    public Result<List<Album>> findPage(@RequestBody(required = false) Album album,
                                        @PathVariable(value = "page") Integer page,
                                        @PathVariable(value = "size") Integer size) {
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<List<Album>>(true,StatusCode.OK,MessageConstant.QUERY_ALBUM_SUCCESS,pageInfo);
    }


    /***
     * Album分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping("/findPage/{page}/{size}")
    public Result<List<Album>> findPage(@PathVariable(value = "page") Integer page,
                                        @PathVariable(value = "size") Integer size) {
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<List<Album>>(true, StatusCode.OK, MessageConstant.QUERY_ALBUM_SUCCESS,pageInfo);
    }

    /***
     * 多条件搜索品牌数据,@RequestBody(required = false) 参数可以是null
     * @param album
     * @return
     */
    @PostMapping("/findList")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album) {
        List<Album> albumList = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, MessageConstant.QUERY_ALBUM_SUCCESS,albumList);
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<Album> delete(@PathVariable(value = "id") Long id) {
        albumService.delete(id);
        return new Result<Album>(true, StatusCode.OK, MessageConstant.DELETE_ALBUM_SUCCESS);
    }


    /***
     * 修改Album数据
     * @param album
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result<Album> update(@RequestBody Album album,
                                @PathVariable(value = "id") Long id) {
        //设置主键值
        album.setId(id);
        //进行更新
        albumService.update(album);
        return new Result<Album>(true, StatusCode.OK, MessageConstant.EDIT_ALBUM_SUCCESS);
    }


    /***
     * 新增Album数据
     * @param album
     * @return
     */
    @PostMapping
    public Result<Album> add(@RequestBody Album album) {
        albumService.add(album);
        return new Result<>(true, StatusCode.OK, MessageConstant.ADD_ALBUM_SUCCESS);
    }


    /***
     * 根据ID查询Album数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable(value = "id") Long id){
        Album album = albumService.findById(id);
        return new Result<Album>(true,StatusCode.OK,MessageConstant.QUERY_ALBUM_SUCCESS,album);
    }


    /***
     * 查询Album全部数据
     * @return
     */
    @GetMapping
    public Result<List<Album>> findAll(){
        List<Album> albumList = albumService.findAll();
        return new Result<>(true,StatusCode.OK,MessageConstant.QUERY_ALBUM_SUCCESS,albumList);
    }

}
