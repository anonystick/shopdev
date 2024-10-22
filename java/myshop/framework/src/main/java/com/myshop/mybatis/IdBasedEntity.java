package com.myshop.mybatis;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Base Entity Class for Database
 *
 * @author vantrang
 * @since 2024/10/16
 */
@Data
@JsonIgnoreProperties(value = {"handler", "fieldHandler"})
@AllArgsConstructor
@NoArgsConstructor
public abstract class IdBasedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
}