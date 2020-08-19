package com.geekerstar.yolo3.controller;

import com.geekerstar.yolo3.domain.entity.BoxPosition;
import com.geekerstar.yolo3.domain.entity.Recognition;
import com.geekerstar.yolo3.domain.vo.ViewObject;
import com.geekerstar.yolo3.util.ObjectDetector;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2020/8/19 11:26
 * @description
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class Yolo3Controller {

    private final ObjectDetector objectDetector;

    @PostMapping("/recognize")
    @ResponseBody
    public List<ViewObject> recognize(@RequestParam("image") MultipartFile image) throws IOException {

        byte[] imageContent = IOUtils.toByteArray(image.getInputStream());

        // 少有人用java做人工智能，因为通过jvm调用CPU的计算效率太低了。
        // 如果使用底层驱动调用GPU计算，可以在近实时时间(小于50毫秒)完成一张图片的识别。
        List<Recognition> objects = objectDetector.detect(imageContent);

        List<ViewObject> result = objects.stream()
                .map(r -> {
                    BoxPosition box = r.getLocation();
                    int probability = Double.valueOf(Math.ceil(r.getConfidence() * 100.0)).intValue();
                    ViewObject ViewObject = new ViewObject(r.getId(), r.getTitle(), probability, (int) box.getLeft(), (int) box.getTop(), (int) box.getRight(), (int) box.getBottom());
                    return ViewObject;
                }).collect(Collectors.toList());

        return result;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}

