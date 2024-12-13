package com.remember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.remember.domain.LoginUser;
import com.remember.domain.UserTbl;
import com.remember.mapper.UserTblMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/26 22:05
 * @Description : 实现UserDetailsService，重写loadUserByUsername方法
 */
@Service // 注入到spring容器中
@RequiredArgsConstructor // 构造器注入
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserTblMapper userTblMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<UserTbl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserTbl::getUsername, username);
        UserTbl userTbl = userTblMapper.selectOne(wrapper);
        if (userTbl == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        //TODO  查询对应的权限信息

        // 把查询到的用户信息封装成UserDetails对象返回
        return new LoginUser(userTbl);
    }
}
