package com.geekerstar.yolo3.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author geekerstar
 * @date 2020/8/19 10:55
 * @description
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewObject {

    /**
     * 类别编号
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 可能性
     */
    private Integer probability;

    /**
     * 坐标x1
     */
    private Integer x1;

    /**
     * 坐标y1
     */
    private Integer y1;

    /**
     * 坐标x2
     */
    private Integer x2;

    /**
     * 坐标y2
     */
    private Integer y2;
}
