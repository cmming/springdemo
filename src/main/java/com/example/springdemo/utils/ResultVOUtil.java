package com.example.springdemo.utils;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.enums.ResultEnum;

/**
 * 返回值工具类
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.SCCESS.getCode());
        resultVO.setMsg(ResultEnum.SCCESS.getMessage());
        return resultVO;
    }
    public static ResultVO success () {
        return success(null);
    }

    public static ResultVO error (Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }

    public static ResultVO error (Integer code, String msg, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return  resultVO;
    }

    public static ResultVO error (ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return  resultVO;
    }
    public static ResultVO error (ResultEnum resultEnum, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        resultVO.setData(data);
        return  resultVO;
    }

}
