package com.helper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.sql.SQLOutput;

public class Hooks {
    public Scenario scenario;

    @Before()
    public void before(Scenario scenario){
        System.out.println("i am start of scenario");
      this.scenario=scenario;

    }
    @After()
    public  void after(){
        System.out.println("i am end of scenario");
    }
}
