package com.raipeng.anotation;

/**
 * Created by xiaofan on 2015/4/20.
 */
public class Apple {

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String fruitColor;

    @Perfact(perfact = "好的")
    public void isPerfact() {

    }

}
