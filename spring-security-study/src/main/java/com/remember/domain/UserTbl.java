package com.remember.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author remember
 * @since 2024-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_tbl")
public class UserTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("name")
    private String name;

    @TableField("avatar")
    private String avatar;

    @TableField("password")
    private String password;

    @TableField("gender")
    private Integer gender;

    @TableField("Information_id")
    private Integer informationId;

    @TableField("age")
    private Integer age;

    @TableField("phone")
    private String phone;

    @TableField("token")
    private String token;

    @TableField("state")
    private Integer state;

    @TableField("deleted")
    private String deleted;

    @TableField("when_created")
    private LocalDateTime whenCreated;


}
