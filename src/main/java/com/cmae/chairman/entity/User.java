package com.cmae.chairman.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("`user`")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Id")
    private Integer id;

    @TableField("LoginName")
    private String loginName;

    @TableField("Password")
    private String password;

    @TableField("IsAction")
    private String isAction;

    @TableField("CreateTime")
    private LocalDateTime createTime;
}
