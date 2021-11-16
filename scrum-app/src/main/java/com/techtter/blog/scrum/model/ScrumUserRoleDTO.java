package com.techtter.blog.scrum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrumUserRoleDTO {


    private Long id;

    private String roleName;

    private String rolePermission;

    private Date createTime;

    private Date modifyTime;

    private String text1;


}
