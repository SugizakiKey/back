package com.back.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import com.back.entity.UserBuyInfo;
import com.back.entity.UserSellInfo;
import com.back.response.Result;
import com.back.service.UserBuyInfoService;
import com.back.service.UserSellInfoService;
import com.back.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author songjie
 * @since 2022-10-21 18:57:06
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends ApiController {

    @Resource
    private UserSellInfoService userSellInfoService;
    @Resource
    private UserBuyInfoService userBuyInfoService;

    @PostMapping("userSellInfo")
    public Result userSellInfo(@RequestBody UserSellInfo userSellInfo){
        return ResultUtil.success(userSellInfoService.saveUserSellInfo(userSellInfo));
    }
    @PostMapping("userBuyInfo")
    public Result userBuyInfo(@RequestBody UserBuyInfo userBuyInfo){
        return ResultUtil.success(userBuyInfoService.saveUserBuyInfo(userBuyInfo));
    }

}

