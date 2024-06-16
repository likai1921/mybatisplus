package org.base.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.base.mybatis.entity.GalaxyLot;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
@Mapper
public interface GalaxyLotsMapper extends BaseMapper<GalaxyLot> {
    /**
     * 多表查询
     *
     * @return
     */
    @Select("select a.id,a.lot_id ,a.status from galaxy_bstock.glx_lots a ,galaxy_bstock.costco_lots b  where a.lot_id =b.lot_id")
    List<GalaxyLot> getGalaxyLotsList();

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Select("select a.id,a.lot_id ,a.status from galaxy_bstock.glx_lots a ,galaxy_bstock.costco_lots b  where a.lot_id =b.lot_id and a.lot_id=#{lot_id}")
    IPage<GalaxyLot> getGalaxyLotsPages(Page<GalaxyLot> page, @Param("lot_id") String lotId);


    @Select("select a.id,a.lot_id ,a.status ,a.title ,a.supplier_id  ,UNIX_TIMESTAMP(a.end_date) close_date ,a.end_date from galaxy_bstock.glx_lots a inner join galaxy_bstock.costco_lots b  on a.lot_id =b.lot_id  ${ew.customSqlSegment}")
    IPage<GalaxyLot> getGalaxyLotsPagesByWrapper(Page<GalaxyLot> page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
