package org.base.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.base.mybatis.controller.params.LotsQuery;
import org.base.mybatis.entity.GalaxyLot;

import java.util.List;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
public interface IGalaxyLotsService extends IService<GalaxyLot> {
    /**
     * 多表查询
     *
     * @return
     */
    List<GalaxyLot> getGalaxyLotList();

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    IPage<GalaxyLot> getGalaxyLotsPages(Page<GalaxyLot> page, String lotId);

    /**
     * 分页查询 -自定义wrapper
     *
     * @param page
     * @param lotsQuery
     * @return
     */
    IPage<GalaxyLot> getGalaxyLotsPagesByWrapper(Page<GalaxyLot> page, LotsQuery lotsQuery);
}
