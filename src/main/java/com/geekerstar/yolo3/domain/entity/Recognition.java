package com.geekerstar.yolo3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author geekerstar
 * @date 2020/8/19 10:54
 * @description
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Recognition {

    private final Integer id;
    private final String title;
    private final Float confidence;
    private BoxPosition location;

}
