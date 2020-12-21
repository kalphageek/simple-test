package me.kalpha.resttempletedemo.entity;


public class Hello {
    public Hello() {
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    String hello;

    public Hello(String hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "hello='" + hello + '\'' +
                '}';
    }
}
