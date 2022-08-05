import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public Scenario scenario;

    @Before
    public void before(Scenario scenario){
      this.scenario=scenario;  
    }
}
