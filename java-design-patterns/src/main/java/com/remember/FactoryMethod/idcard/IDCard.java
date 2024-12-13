package com.remember.FactoryMethod.idcard;

import com.remember.FactoryMethod.framework.Product;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 22:02
 * @Description :
 */
public class IDCard extends Product {
    private String owner;

    IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("使用" + owner + "的ID卡");
    }

    public String getOwner() {
        return owner;
    }
}
