package com.guava.example;


import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author weizhao.dong
 * @Date 2023/12/5 22:15
 * @Version 1.0
 */
public class PreconditionsTest {
    public void processNumber(int number) {
        Preconditions.checkArgument(number > 0, "Number must be positive");
        // 进行处理逻辑
    }
    @Test
    public void testCheckArgument() {
        processNumber(10);// 正常调用，参数符合条件
        processNumber(-5); // 抛出IllegalArgumentException异常，参数不符合条件，异常消息中包含具体的参数值
    }

    public void processNumberTemplate(int number) {
        Preconditions.checkArgument(number > 0, "Number must be positive: %s", number);
        // 进行处理逻辑
    }
    @Test
    public void testCheckArgumentTemplate() {
        processNumberTemplate(10);// 正常调用，参数符合条件
        processNumberTemplate(-5); // 抛出IllegalArgumentException异常，参数不符合条件，异常消息中包含具体的参数值
    }


    @Test
    public void testCheckElementIndex() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        int index = 3;
        Preconditions.checkElementIndex(index, list.size(), "Invalid index");

        String element = list.get(index);
        System.out.println("Element at index " + index + ": " + element);
    }


    @Test
    public void testCheckNotNull() {
        String name = "John Doe";
        Preconditions.checkNotNull(name, "Name cannot be null");
        System.out.println("Name: " + name);
    }

    @Test
    public void testCheckState() {
        int[] validStates = {-1, 0, 1};
        int givenState = 8;
        String message = "You have entered an invalid state";
        Preconditions.checkState(Arrays.binarySearch(validStates, givenState) > 0, message);
    }


}
