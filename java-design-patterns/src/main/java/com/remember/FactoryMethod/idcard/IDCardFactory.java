package com.remember.FactoryMethod.idcard;

import com.remember.FactoryMethod.framework.Factory;
import com.remember.FactoryMethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 22:03
 * @Description :
 */
public class IDCardFactory extends Factory {
    private List owners = new ArrayList();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
}
