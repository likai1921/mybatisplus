package org.base.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.base.mybatis.controller.params.LotsQuery;
import org.base.mybatis.controller.params.SysUserQuery;
import org.base.mybatis.entity.GalaxyLot;
import org.base.mybatis.entity.SysUser;
import org.base.mybatis.service.IGalaxyLotsService;
import org.base.mybatis.service.ISysUserService;
import org.base.mybatis.utils.keys.RedisKyes;
import org.base.mybatis.utils.response.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2021-01-12
 */
@RestController
@RequestMapping("/user")
@Api(tags = "测试类")
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IGalaxyLotsService galaxyLotsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation("测试类")
    @GetMapping("/test")
    public Object test() {
        log.info("测试类请求数据");
        IPage<SysUser> page = new Page<>(1, 50);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        SysUser sysUser = new SysUser();
        sysUser.setId(82L);
        wrapper.setEntity(sysUser);
        IPage<SysUser> pages = sysUserService.page(page);
        // QueryWrapper queryWrapper =new QueryWrapper<>();

        log.warn("警告信息");
        log.error("错误提示");
        log.debug("debug信息");
        return pages;
    }

    @ApiOperation("插入数据")
    @PostMapping("/update")
    public Object update(@RequestBody SysUser sysUser) {
        if (sysUser != null) {
            sysUserService.saveOrUpdate(sysUser);
            log.info("mybatis 插入数据：{}", sysUser);
        }
        return "success";
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable long id) {
        boolean b = sysUserService.removeById(id);
        log.info("删除id :{}", b);
        return "success";
    }

    @ApiOperation("条件查询")
    @PostMapping("/queryKeyWords")
    public Object deleteUser(@RequestBody SysUserQuery query) {

        long start = 1608548585000L;
        long end = 1608620655000L;
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //  queryWrapper.like("login_name" ,query.getKeywords()).eq("tel",query.getLoginName());
        //   queryWrapper.apply("UNIX_TIMESTAMP(update_date) >= UNIX_TIMESTAMP(‘"+end+"‘)");
        queryWrapper.apply("UNIX_TIMESTAMP(update_date) * 1000>= " + start);
        queryWrapper.apply("UNIX_TIMESTAMP(update_date)* 1000 <= " + end);
        queryWrapper.like("tel", query.getKeywords());
        List<SysUser> list = sysUserService.list(queryWrapper);

        return list;
    }

    @ApiOperation("多表查询")
    @PostMapping("/test/tow_query")
    public Object tow_query() {
        PageHelper.startPage(2, 50);
        List<GalaxyLot> galaxyLotList = galaxyLotsService.getGalaxyLotList();
        galaxyLotList.stream().filter(s -> s.getStatus().contains("R")).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(galaxyLotList);

        log.info("自定义多表查询结果：{}", pageInfo);

        return pageInfo;
    }

    @ApiOperation("分页查询")
    @PostMapping("/page_query")
    public HttpResponse page_query(@RequestBody LotsQuery lotsQuery) {
        //Assert.isNull(lotsQuery.getStatus(),"提交的数据为空！");
        // Assert.isNull(lotsQuery.getStatus().,"状态为空");
        IPage<GalaxyLot> galaxyLotsPages = galaxyLotsService.getGalaxyLotsPagesByWrapper(new Page<GalaxyLot>(lotsQuery.getPageNum(), lotsQuery.getPageSize()), lotsQuery);

        log.info("分页查询：{}", galaxyLotsPages);
        //HttpResponse.success(new HttpResponse.Pages(galaxyLotsPages.getTotal(),galaxyLotsPages.getSize(),galaxyLotsPages.getPages(),galaxyLotsPages.getRecords()) )
        return HttpResponse.success(new HttpResponse.Pages(galaxyLotsPages.getTotal(), galaxyLotsPages.getSize(), galaxyLotsPages.getCurrent(), galaxyLotsPages.getRecords()));
        //return HttpResponse.failed(300,"token error");
    }

    @ApiOperation("keys查询")
    @GetMapping("/keys_query")
    public HttpResponse page_query() {

        RedisSerializer<?> keySerializer = redisTemplate.getKeySerializer();
        Set<String> keys = redisTemplate.keys("*");
        keys.forEach(item -> {
            int size = redisTemplate.opsForValue().size(item).byteValue();

            log.info("获取{}的大小：{}", item, size);
        });
        int size = redisTemplate.keys("*").size();
        log.info("获取的key：{}", size);


        return HttpResponse.success(keys);
    }

    @ApiOperation("zset查询")
    @GetMapping("/zset_query")
    public HttpResponse zset_query() {

        Double user1 = redisTemplate.opsForZSet().incrementScore(RedisKyes.Z_SET_KEYS, "user1", 1);

        Double user11 = redisTemplate.opsForZSet().incrementScore(RedisKyes.Z_SET_KEYS, "user1", 1);
        Double user12 = redisTemplate.opsForZSet().incrementScore(RedisKyes.Z_SET_KEYS, "user1", 1);
        Double user2 = redisTemplate.opsForZSet().incrementScore(RedisKyes.Z_SET_KEYS, "user2", 1);
        Set<String> strings = redisTemplate.opsForZSet().rangeByScore(RedisKyes.Z_SET_KEYS, 0, 9);
        //System.out.println(strings);
        Set<String> range = redisTemplate.opsForZSet().range(RedisKyes.Z_SET_KEYS, 0, 9);
        Set<ZSetOperations.TypedTuple<String>> typedTuples1 = redisTemplate.opsForZSet().rangeWithScores(RedisKyes.Z_SET_KEYS, 0, 9);

        redisTemplate.opsForZSet().incrementScore(RedisKyes.Z_SET_KEYS, "user2", -1);
        Double user3 = redisTemplate.opsForZSet().score(RedisKyes.Z_SET_KEYS, "user3");
        Double user111 = redisTemplate.opsForZSet().score(RedisKyes.Z_SET_KEYS, "user1");
        log.info("user3 :{} ,user21111{}", user3, user111);
        typedTuples1.forEach(item -> {
            log.info("value的信息：{} ,  score的信息：{}", item.getValue(), item.getScore());
        });
        return HttpResponse.success(typedTuples1);
    }

    public static void main(String[] args) throws IOException {
        File[] roots = File.listRoots();
        System.out.println();
        //Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c osk");// 通过屏幕软键盘

    }
}

