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
public class UserDTO {


    private Long id;

    private String userName;

    private String userPassword;

    private int status;

    private Date createTime;

    private Date modifyTime;

    private int roleId;

    private String phone;


}
