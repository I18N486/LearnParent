package com.zst.mybatis.biz.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zst.mybatis.api.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zst
 * @since 2021-04-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{

    @Select("select name from user where id = #{id}")
    String getNameById(@Param("id") Long id);

    List<User> getByWrapper(@Param(Constants.WRAPPER) Wrapper ew);
}
