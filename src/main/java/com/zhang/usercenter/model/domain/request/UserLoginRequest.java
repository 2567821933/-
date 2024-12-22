package com.zhang.usercenter.model.domain.request;


import lombok.Data;

import java.io.Serializable;

@Data //处于lombok中的注解
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -6459913915049852130L;
    private String userAccount;
    private String userPassword;
}
