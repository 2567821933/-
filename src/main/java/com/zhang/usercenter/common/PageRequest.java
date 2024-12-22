package com.zhang.usercenter.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 8547456989214330012L;

    protected int pageSize = 10;
    protected int pageNum = 1;
}
