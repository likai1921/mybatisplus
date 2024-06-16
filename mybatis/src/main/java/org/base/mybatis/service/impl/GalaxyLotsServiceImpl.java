package org.base.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.base.mybatis.controller.params.LotsQuery;
import org.base.mybatis.entity.GalaxyLot;
import org.base.mybatis.mapper.GalaxyLotsMapper;
import org.base.mybatis.service.IGalaxyLotsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
@Service
public class GalaxyLotsServiceImpl extends ServiceImpl<GalaxyLotsMapper, GalaxyLot> implements IGalaxyLotsService {

    @Resource
    private GalaxyLotsMapper galaxyLotsMapper;

    @Override
    public List<GalaxyLot> getGalaxyLotList() {
        return galaxyLotsMapper.getGalaxyLotsList();
    }

    @Override
    public IPage<GalaxyLot> getGalaxyLotsPages(Page<GalaxyLot> page, String lotId) {
        return galaxyLotsMapper.getGalaxyLotsPages(page, lotId);
    }

    @Override
    public IPage<GalaxyLot> getGalaxyLotsPagesByWrapper(Page<GalaxyLot> page, LotsQuery lotsQuery) {
        QueryWrapper<GalaxyLot> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(lotsQuery.getStatus())) {
            queryWrapper.eq("a.status", lotsQuery.getStatus());
        }
        if (StringUtils.isNotBlank(lotsQuery.getLotId())) {
            queryWrapper.eq("a.lot_id", lotsQuery.getLotId());
        }
        if (StringUtils.isNotBlank(lotsQuery.getKeywords())) {
            queryWrapper.and(wapper -> wapper.like("a.title", lotsQuery.getKeywords()).or().like("a.lot_id", lotsQuery.getKeywords()));
        }
        //拼接   queryWrapper.apply()
        if (lotsQuery.getStartDate() != 0 && lotsQuery.getEndDate() != 0) {
            queryWrapper.and(wapper -> wapper.gt("UNIX_TIMESTAMP(a.end_date)", lotsQuery.getStartDate() / 1000).le("UNIX_TIMESTAMP(a.end_date)", lotsQuery.getEndDate() / 1000));
        }
        if (lotsQuery.getStatus().equals("R") || lotsQuery.getStatus().equals("B") || lotsQuery.getStatus().equals("P") || lotsQuery.getStatus().equals("A")) {

            queryWrapper.orderByAsc("a.end_date");
        } else {
            queryWrapper.orderByDesc("a.end_date");
        }

        IPage<GalaxyLot> iPage = galaxyLotsMapper.getGalaxyLotsPagesByWrapper(page, queryWrapper);
 /*       List<GalaxyLot> collect = iPage.getRecords().stream().filter(s -> s.getTitle().contains("A/B")).collect(Collectors.toList());
        iPage.setRecords(collect);
        iPage.setTotal(collect.size());*/
        return iPage;
    }
}
