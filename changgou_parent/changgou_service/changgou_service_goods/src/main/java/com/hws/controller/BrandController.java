package com.hws.controller;

import com.github.pagehelper.PageInfo;
import com.hws.goods.pojo.Brand;
import com.hws.service.BrandService;
import entity.Result;
import entity.StatusCode;
import io.swagger.models.auth.In;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:BrandController
 * Package:com.hws.controller
 * Description:
 *
 * @ date:2020/10/12 16:13
 * @ author:hws
 */
@RestController
@RequestMapping("/brand")
/**
 * 跨域问题：A域名 去访问 B域名的数据时
 *  1、域名
 *  2、请求端口 ：8080  vs ：8081
 *  3、协议不一样的时候 https  vs   http
 */
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @Description: //查询所有Brand信息
     * @Date 17:59 2020/10/12
     * @Param []
     **/
    @GetMapping()
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * @Description: //根据ID进行查询brand
     * @Author hws
     * @Date 18:27 2020/10/12
     * @Param [id]
     **/
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * @Description: //插入brand
     * @Author hws
     * @Date 18:37 2020/10/12
     * @Param [brand]
     **/
    @PostMapping()
    public Result insert(@RequestBody Brand brand) {
        brandService.insert(brand);
        return new Result(true, StatusCode.OK, "插入成功");
    }

    /**
     * @Description: //修改成功
     * @Author hws
     * @Date 18:41 2020/10/12
     * @Param [brand, id]
     **/
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Description: //删除成功
     * @Author hws
     * @Date 19:11 2020/10/12
     * @Param [id]
     **/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * @Description: 模糊查询
     * @Author hws
     * @Date 19:26 2020/10/12
     * @Param [brand]
     **/
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> brandList = brandService.findList(brand);
        return new Result(true, StatusCode.OK, "模糊搜索成功",brandList);
    }

    /**
     * @Description: 分页查询
     * @Author hws
     * @Date 19:50 2020/10/12
     * @Param [page, size]
     **/
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页搜索成功",pageInfo);
    }

    /**
     * @Description: 分页按照条件进行查询
     * @Author hws
     * @Date 20:03 2020/10/12
     * @Param [brand, page, size]
     **/
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Brand brand,
                                     @PathVariable("page")Integer page,
                                     @PathVariable("size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result(true, StatusCode.OK, "分页搜索成功",pageInfo);
    }

}
