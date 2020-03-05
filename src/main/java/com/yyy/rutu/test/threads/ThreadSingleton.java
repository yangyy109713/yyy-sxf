package com.yyy.rutu.test.threads;

public class ThreadSingleton {
    public static void main(String[] args){
        Runnable r = () -> {
            Single.getInstance();
        };

        for (int i = 0; i < 100; i++){
            new Thread(r).start();
        }
    }
}

class Single{
    private Single(){
        System.out.println("一个 Single 被实例化了！");
    }

    private static Single instance = null;

    public static synchronized Single getInstance(){
        /*synchronized ("A"){
            if (instance == null){
                instance = new Single();
            }
        }*/
        return instance;
    }

}
