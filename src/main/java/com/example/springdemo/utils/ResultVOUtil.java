package com.example.springdemo.utils;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.enums.ResultEnum;

/**
 * 返回值工具类
 */
public class ResultVOUtil {

    /**
     * 成功，并自定义具体的返回值 .
     * @param object 具体的返回值
     * @return ResultVO
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.SCCESS.getCode());
        resultVO.setMsg(ResultEnum.SCCESS.getMessage());
        return resultVO;
    }

    /**
     * 请求成功并且无具体返回值 .
     * @return ResultVO
     */
    public static ResultVO success () {
        return success(null);
    }

    /**
     * 请求错误 .
     * @param code 错误码
     * @param msg 消息
     * @return ResultVO
     */
    public static ResultVO error (Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }

    /**
     * 请求错误，自定义code、msg、data
     * @param code 错误码
     * @param msg 错误消息
     * @param data 数据
     * @return ResultVO
     */
    public static ResultVO error (Integer code, String msg, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return  resultVO;
    }

    /**
     * 根据枚举信息进行返回值中错误状态码和错误消息的自定义 .
     * @param resultEnum 错误枚举
     * @return ResultVO
     */
    public static ResultVO error (ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return  resultVO;
    }

    /**
     * 根据枚举信息进行返回值中错误状态码和错误消息的自定义，并返回具体的错误内容 .
     * @param resultEnum 错误枚举
     * @param data 自定义具体内容
     * @return ResultVO
     */
    public static ResultVO error (ResultEnum resultEnum, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        resultVO.setData(data);
        return  resultVO;
    }

}
