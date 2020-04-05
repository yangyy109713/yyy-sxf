package com.yyy.rutu.test.java8;

public class SubClass /*extends MyClass*/ implements DeInterface, AInterface {
    @Override
    public String def() {
        return DeInterface.super.def();
    }

    public void show(){
        DeInterface.show();
    }
}
