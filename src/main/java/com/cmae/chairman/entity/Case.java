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
@TableName("`case`")
@ApiModel(value = "Case对象", description = "")
public class Case implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Id")
    private Integer id;

    @TableField("Img")
    private String img;

    @TableField("Title")
    private String title;

    @TableField("Content")
    private String content;

    @TableField("Del")
    private String del;

    @TableField("CreateTime")
    private LocalDateTime createTime;
}
