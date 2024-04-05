package com.back.utils;

import com.back.response.Result;

import java.util.Map;

public class ResultUtil {
    public static Result success(Map map){
        return new Result().setData(map).setCode(Integer.valueOf(map.get("code").toString())).setMessage(map.get("message").toString());
    }
    public static Result success(){
        return new Result().setCode(1).setMessage("成功！");
    }
    public static Result success1(Map map){
        return new Result().setData(map).setCode(1).setMessage("成功！");
    }
}
