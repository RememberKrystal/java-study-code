package com.remember.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/23 17:08
 * @Description :
 */
@Setter
@Getter
@ToString
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // 手动指定序列化版本号

    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}