package com.hitTheRoad.server.exception;


import com.hitTheRoad.server.pojo.RespBean;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/*
* 全局处理异常
* */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("没有该数据有关数据，操作失败");
        }
        return RespBean.error("数据库异常，操作失败");
    }
}
