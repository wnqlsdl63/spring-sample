package com.example.sample;

import org.junit.jupiter.api.Test;

import java.util.List;

public class SampleTest {

    @Test
    public void test() {
        List<String> test = List.of("test", "test2");

        List<String> stream = test.stream().filter(e -> e.contains("f")).toList();


    }
}
