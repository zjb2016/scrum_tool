package com.techtter.blog.scrum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name ="users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = User.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private int id;

    @Column(name = "user_name")
    @ApiModelProperty(position = 2)
    private String userName;

    @Column(name = "user_password")
    @ApiModelProperty(position = 3)
    private String userPassword;

    @Column(name = "status")
    @ApiModelProperty(position = 4)
    private int status;

    @Column(name = "create_time", columnDefinition= "TIMESTAMP WITHOUT TIME ZONE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @ApiModelProperty(position = 5)
    private Date createTime;

    @Column(name = "modify_time", columnDefinition= "TIMESTAMP WITHOUT TIME ZONE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @ApiModelProperty(position = 6)
    private Date modifyTime;

    @Column(name = "phone")
    @ApiModelProperty(position = 7)
    private String phone;



}
