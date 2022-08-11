package steps.Definitions;



import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.sql.SQLOutput;

public class Hooks {
    public Scenario scenario;

     @Before
    public void beforeScenario(Scenario scenario){
        System.out.println("*************i am start of scenario****************");
        this.scenario=scenario;

    }
     @After
    public  void afterScenario(){
        System.out.println("*****************i am end of scenario*****************");
    }
}
