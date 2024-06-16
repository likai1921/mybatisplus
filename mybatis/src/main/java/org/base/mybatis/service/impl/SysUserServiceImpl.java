package org.base.mybatis.service.impl;

import org.base.mybatis.entity.SysUser;
import org.base.mybatis.mapper.SysUserMapper;
import org.base.mybatis.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2021-01-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
