package com.remember.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.domain.LoginUser;
import com.remember.domain.UserTbl;
import com.remember.domain.dto.LoginDTO;
import com.remember.mapper.UserTblMapper;
import com.remember.result.Result;
import com.remember.service.IUserTblService;
import com.remember.utils.JwtUtil;
import com.remember.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author remember
 * @since 2024-07-27
 */
@Service
@RequiredArgsConstructor
public class UserTblServiceImpl extends ServiceImpl<UserTblMapper, UserTbl> implements IUserTblService {

    private final AuthenticationManager authenticationManager;

    private final RedisUtil redisUtil;

    @Override
    public Result<UserTbl> login(LoginDTO loginDTO) {
        // 1. AuthenticationManager 进行用户认证
        // 将用户名和密码封装成UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 2. 如果认证失败，给出对应提示
        if (authenticate == null) {
            return Result.error("用户名或密码错误");
        }
        // 3. 如果认证成功，生成token
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        UserTbl userTbl = principal.getUser();
        Integer userId = userTbl.getId();
        // 生成token
        String token = JwtUtil.genAccessToken(userId);
        userTbl.setToken(token);
        this.updateById(userTbl); // 更新token
        // 4. 把完整的用户信息保存到redis中
        redisUtil.set("login:" + userId, userTbl);

        return Result.success(userTbl);
    }
}
