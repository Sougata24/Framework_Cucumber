package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",
     glue = "com.StepDefinition",
        format = { 
                    "pretty",
                    "html:target/Reports",
                }        
        )
public class RunnerTest{}
//public class RunnerTest extends AbstractTestNGCucumberTests  {
//}