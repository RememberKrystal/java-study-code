package com.remember.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.remember.domain.UserTbl;
import com.remember.domain.dto.LoginDTO;
import com.remember.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author remember
 * @since 2024-07-27
 */
public interface IUserTblService extends IService<UserTbl> {

    Result<UserTbl> login(LoginDTO loginDTO);
}
