package com.remember.domain.po;

import lombok.Data;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/17 17:29
 * @Description :
 */
@Data
public class TestEntity {
    private int id;
    private String name;

    public TestEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
