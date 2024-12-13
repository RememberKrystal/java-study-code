package com.remember.Composite;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 22:21
 * @Description :
 */
public abstract class Entry {
    public abstract String getName();
    public abstract int getSize();
    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }
    protected void printList() {
        printList("");
    }
    protected abstract void printList(String prefix);

    @Override
    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
