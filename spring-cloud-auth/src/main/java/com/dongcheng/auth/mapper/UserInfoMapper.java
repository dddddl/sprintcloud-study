package com.dongcheng.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongcheng.auth.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
}
