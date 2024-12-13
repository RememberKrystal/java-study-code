package com.remember.FactoryMethod.framework;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 22:00
 * @Description :
 */
public abstract class Factory {
    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }

    protected abstract Product createProduct(String owner);

    protected abstract void registerProduct(Product product);
}
