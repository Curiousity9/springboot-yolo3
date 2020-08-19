package com.geekerstar.yolo3.domain.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * @author geekerstar
 * @date 2020/8/19 10:52
 * @description
 */
@Getter
@Setter
public class BoundingBox {
    private double x;
    private double y;
    private double width;
    private double height;
    private double confidence;
    private double[] classes;
}
