package com.remember.controller;

import com.remember.domain.dto.LoginDTO;
import com.remember.domain.UserTbl;
import com.remember.result.Result;
import com.remember.service.IUserTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/26 21:56
 * @Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserTblService userTblService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/login")
    public Result<UserTbl> login(@RequestBody LoginDTO loginDTO) {
        return userTblService.login(loginDTO);
    }
}
