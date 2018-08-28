package com.framework.loippi.entity;

import lombok.Data;

import java.util.List;

@Data
public class AclChilds {
    private Long id;
    private String name;
    List<Acl> childs;
}
