package com.zhang.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author zhang
 */

@Data // 用以生成get set方法。
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -6070240701622729762L;//序列化接口的实现
//    如果可序列化的类未显式声明一个 serialVersionUID，则序列化运行时将根据serialVersionUID该类的各个方面为该类计算默认值，如Java对象序列化规范中所述。
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;

}
