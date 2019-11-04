package cl.streamlink.contact.utils;

interface A {
    default void draw() {
        System.err.println("A");
    }
}

interface B {
    default void draw() {
        System.err.println("A");
    }
}

public class D extends C implements B, A {


    @Override
    public void draw() {

    }
}

class C implements B {

}