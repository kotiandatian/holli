package com.framework.loippi.entity;

import lombok.Data;

import java.util.List;

@Data
public class AclRoot {
    private Long id;
    private String name;
    List<AclChilds> childs;
}
