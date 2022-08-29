package com.jackmeng.clijava;

import com.jackmeng.test.FakeInvokable;

public class CommandLineJava {
  public static void main(String[] args) {
    Caller c = new Caller(System.out, new FakeInvokable());
    
  }
}