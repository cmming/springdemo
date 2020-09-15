package com.example.springdemo.utils;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: chenming
 * @Description:控制器的异常处理类(如果不修改响应状态码，响应可以通过添加ResponseBody注解)
 * 前端可以根据返回的状态码进行判断
 * status 404 表示接口不存在
 * status 405 表示接口请求类型不存在
 * @Date: Create in 10:20 2020-09-15
 **/
@Slf4j
@ControllerAdvice
public class ErrorHandler {

    /**
     * 参数异常校验
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity NotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {

        BindingResult bindingResult = e.getBindingResult();

        //rfc4918 - 11.2. 422: Unprocessable Entity
        ResultVO res = MiscUtil.getValidateError(bindingResult);
        log.debug("错误内容：{}",res);
        return new ResponseEntity(res, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * 404异常处理
     * # 出现错误时，直接抛出异常
     * #spring.mvc.throw-exception-if-no-handler-found=true
     * # 禁用静态资源的自动映射，如不禁用不存在的url将被映射到，servlet不有机会抛出异常（会导致静态资源不可用）
     * #spring.resources.add-mappings=false
     */
//    @ExceptionHandler(value = NoHandlerFoundException.class)
//    public ResponseEntity<ResultVO> NoHandlerFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
//
//        log.debug("异常详情", e);
//
//        ResultVO res = ResultVOUtil.error(ResultEnum.NOT_FOUND);
//        return new ResponseEntity<ResultVO>(res, HttpStatus.NOT_FOUND);
//    }

    /**
     *  默认异常处理，前面未处理
     */
//    @ExceptionHandler(value = Throwable.class)
//    public ResponseEntity<ResultVO> defaultHandler(HttpServletRequest req, Exception e) throws Exception {
//
//        ResultVO res = ResultVOUtil.error(ResultEnum.SERVER_ERR0R);
//        log.debug("异常详情", e);
//
//        return new ResponseEntity<ResultVO>(res, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
