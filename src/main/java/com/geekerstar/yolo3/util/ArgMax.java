package com.geekerstar.yolo3.util;

/**
 * @author geekerstar
 * @date 2020/8/19 11:22
 * @description
 */
public class ArgMax {

    private double[] params;

    public ArgMax(double[] params) {
        this.params = params;
    }

    public Result getResult() {
        int maxIndex = 0;
        for (int i = 0; i < params.length; i++) {
            if (params[maxIndex] < params[i]) {
                maxIndex = i;
            }
        }

        return new Result(maxIndex, params[maxIndex]);
    }

    public class Result {
        private int index;
        private double maxValue;

        public Result(int index, double maxValue) {
            this.index = index;
            this.maxValue = maxValue;
        }

        public int getIndex() {
            return index;
        }

        public double getMaxValue() {
            return maxValue;
        }
    }
}
