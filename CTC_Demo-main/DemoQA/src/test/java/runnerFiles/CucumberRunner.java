package runnerFiles;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/cucumber-report-html", "json:target/cucumber-results.json"}, 
		tags = {"@demo" }, 
		features = { "src/test/resources/features" },
		monochrome =true,
		glue = { "steps" })

public class CucumberRunner {

}
