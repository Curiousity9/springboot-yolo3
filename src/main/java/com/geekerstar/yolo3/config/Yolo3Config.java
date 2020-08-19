package com.geekerstar.yolo3.config;

import com.geekerstar.yolo3.util.ObjectDetector;
import com.geekerstar.yolo3.util.YOLOClassifier;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2020/8/19 10:49
 * @description
 */
@Component
public class Yolo3Config {

    /**
     * 读取训练类别
     */
    @Bean(name = "cocoClasses")
    public String[] getCocoClasses() throws IOException {
        String classContent = IOUtils.resourceToString(
                "model/coco_classes.txt",
                StandardCharsets.UTF_8,
                Yolo3Config.class.getClassLoader());
        return classContent.split("\n");
    }

    /**
     * 构造分类器
     */
    @Bean(name = "yoloClassifier")
    public YOLOClassifier getYOLOClassifier(
            @Qualifier("cocoClasses") String[] cocoClasses) throws IOException {

        String anchorContent = IOUtils.resourceToString(
                "model/coco_anchors.txt",
                StandardCharsets.UTF_8,
                Yolo3Config.class.getClassLoader());
        List<Double> anchorList = Arrays.stream(anchorContent.split(","))
                .map(c -> Double.parseDouble(c.trim()))
                .collect(Collectors.toList());
        double[] anchors = new double[anchorList.size()];
        for (int i = 0; i < anchorList.size(); i++) {
            anchors[i] = anchorList.get(i);
        }

        return new YOLOClassifier(anchors, cocoClasses.length);
    }

    /**
     * 构造对象探测器
     */
    @Bean(name = "objectDetector")
    public ObjectDetector getObjectDetector(
            @Qualifier("cocoClasses") String[] cocoClasses,
            @Qualifier("yoloClassifier") YOLOClassifier yoloClassifier
    ) throws IOException {
        byte[] modelContent = IOUtils.resourceToByteArray(
                "model/coco_model.pb",
                Yolo3Config.class.getClassLoader());
        return new ObjectDetector(modelContent, Arrays.asList(cocoClasses), yoloClassifier);
    }
}
