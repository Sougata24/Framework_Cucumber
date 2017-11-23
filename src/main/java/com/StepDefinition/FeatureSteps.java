package com.StepDefinition;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import com.framework.page._Login_Home;
import com.framework.page._ReqDetails;
import com.framework.utility.ConnectORproperties;
import com.framework.utility.HighlightElement;
import com.framework.utility.ReportExcel;
import com.framework.utility.ReusableAsset;
import com.framework.utility.onErrorScreenshot;
import com.parent.BaseClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeatureSteps extends BaseClass {

	ConnectORproperties conORPropObj = new ConnectORproperties();
	String GetLabel;
	String Case_ID;

	// public WebDriver driver;
	/* ================================ Hooks ============================ */
	@Before
	public void setUp() throws InterruptedException {
		BaseClass.openApplication();
	}

	@After
	public void cleanUp(Scenario scenario) throws InterruptedException {

		String nameOfScenario = scenario.getName();
		String currClassName = this.getClass().getName().toString();
		Collection<String> getTagName = scenario.getSourceTagNames();
		String getStatus = scenario.getStatus();
		String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ");

		if (scenario.isFailed()) {

			onErrorScreenshot oErrorScrnsht = new onErrorScreenshot();
			oErrorScrnsht.takeOnErrorScreenshot(nameOfScenario, currClassName, driver);
		}

		ReportExcel objReExl = new ReportExcel();
		try {
			objReExl.createReportFile(rawFeatureName, nameOfScenario, getTagName, getStatus);
		} catch (Exception e) {

			e.printStackTrace();
		}

		BaseClass.closeApplication();

	}

	/* ================================ Given ============================ */

	@Given("^Launch Pega_Local_Server URL$")
	public void launch_SP_Management_URL() throws Throwable {
		driver.get("http://10.74.54.79:8080/prweb/PRServlet");
	}

	
	/* ================================ When ============================ */

	@When("^Enter UserID & Password of operator$")
	public void enter_UserID_Password() throws Throwable {
		_Login_Home.loginApp(driver, "Dummy_User");
		Thread.sleep(2000);
	}

	/* ================================ Then ============================ */

	@Then("^Validate Create option$")
	public void validate_Create_option() throws Throwable {
		//driver.switchTo().defaultContent();
		String GetLabel = ReusableAsset.getLebelTest("//*[@data-test-id='201603310245530801340']", driver);
		HighlightElement.highLightElement("//*[@data-test-id='201603310245530801340']", driver);
		System.out.println(GetLabel);
		Thread.sleep(2000);
		Assert.assertEquals(GetLabel, "Create My Case");
	}
	
	@Then("^Validate Case ID$")
	public void Validate_Case_ID() throws Throwable {
		driver.switchTo().defaultContent();
		//driver.switchTo().frame("PegaGadget1Ifr");
		Thread.sleep(2000);
		/*String GetLabel = ReusableAsset.getLebelTest("//*[@class='case_title']", driver);
		HighlightElement.highLightElement("//*[@class='case_title']", driver);*/
		
		//String GetLabel = ReusableAsset.getLebelTest("//*[@data-test-id='2016010509000408137898']", driver);
		HighlightElement.highLightElement("//*[@data-test-id='2016033102475004767540']", driver);
		Thread.sleep(2000);
		ReusableAsset.buttonClick("//*[@data-test-id='2016033102475004767540']", driver);
		List<WebElement> el = driver.findElements(By.xpath("//*[@class='menu-item-title']"));
		el.get(18).click();
		Thread.sleep(2000);
	
		//Assert.assertTrue(GetLabel.contains("GE-"));
	}

	/* ================================ And ============================ */

	@And("^Create New case & enter case details$")
	public void Create_New_Case() throws Throwable {
		ReusableAsset.buttonClick("//*[@data-test-id='201603310245530801340']", driver);
		Thread.sleep(2000);
		List<WebElement> el = driver.findElements(By.xpath("//*[@class='menu-item-title']"));
		el.get(1).click();
		Thread.sleep(500);
		driver.switchTo().frame("PegaGadget1Ifr");
		Thread.sleep(500);
		ReusableAsset.enterText("//*[@data-test-id='2015081105155605591884']", "Dummy Case", driver);
		ReusableAsset.enterText("//*[@id='EXPAND']", "Dummy Summary", driver);
		ReusableAsset.enterText("//*[@id='pyProblemReason']", "Dummy reason", driver);
		ReusableAsset.buttonClick("//*[@class='pzbtn-mid']", driver);	
	}
	
	
}
