package com.cmae.chairman.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2024-09-14
 */
@Getter
@Setter
@TableName("job")
@ApiModel(value = "Job对象", description = "")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @TableField("Job")
    private String job;

    @TableField("Type")
    private String type;

    @TableField("Location")
    private String location;

    @TableField("Statement")
    private String statement;

    @TableField("Requirement")
    private String requirement;

    @TableField("CreateTime")
    private LocalDateTime createTime;
}
