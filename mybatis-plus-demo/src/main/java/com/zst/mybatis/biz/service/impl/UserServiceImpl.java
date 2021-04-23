package com.zst.mybatis.biz.service.impl;

import com.zst.mybatis.api.pojo.entity.User;
import com.zst.mybatis.biz.dao.UserMapper;
import com.zst.mybatis.biz.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zst
 * @since 2021-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
