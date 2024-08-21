package com.assigments.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assigments.base.SessionManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class LambdaTestAutomation extends SessionManager {

	Page page;

	@BeforeMethod
	public void setUp() {
		page = startSession();
	}

	@Test(enabled = true)
	public void tc_TestScenario001() {
		try {
			page.navigate("https://www.lambdatest.com/selenium-playground");
			page.click("//a[normalize-space()='Simple Form Demo']");
			if (!page.url().contains("simple-form-demo")) {
				throw new RuntimeException("URL does not contain 'simple-form-demo'");
			}
			String message = "Welcome to LambdaTest";
			page.fill("input#user-message", message);
			page.click("#showInput");

			String displayedMessage = page.textContent("#message");
			Assert.assertEquals(displayedMessage, message, "The message displayed does not match the input.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Test ID: YXQKH-7OBPB-TBHCQ-WIO6K

	@Test(enabled = true)
	public void tc_TestScenario002() {
		try {
			page.navigate("https://www.lambdatest.com/selenium-playground");
			page.click("//a[normalize-space()='Drag & Drop Sliders']");
			Locator slider = page.locator("input[type='range'][value='15']");
			Locator output = page.locator("output#rangeSuccess");
			double currentValue = Double.parseDouble(slider.getAttribute("value"));
			double targetValue = 95;
			double sliderWidth = slider.boundingBox().width;
			double moveOffset = (targetValue - currentValue) / 100 * sliderWidth;
			slider.dragTo(slider, new Locator.DragToOptions().setTargetPosition(moveOffset + 65, 0));
			String value = output.textContent();
			Assert.assertEquals(value, "95", "Validation failed: The range value is " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Test ID: QBW3S-DNCXO-VSZK9-LTAG2

	@Test(enabled = true)
	public void tc_TestScenario003() {
		try {
			page.navigate("https://www.lambdatest.com/selenium-playground");
			page.click("//a[normalize-space()='Input Form Submit']");
			page.click("//button[normalize-space()='Submit']");
			Locator nameField = page.locator("input[name='name']");
			boolean isFieldRequired = (boolean) nameField.evaluate("element => element.validity.valueMissing");
			Assert.assertTrue(isFieldRequired, "Error message validated: 'Please fill in the fields'");
			page.fill("input[name='name']", "Sanoj Swaminathan");
			page.fill("//input[@id='inputEmail4']", "sanojswaminathan@example.com");
			page.fill("//input[@id='inputPassword4']", "Test123");
			page.fill("//input[@id='company']", "SNJ Inc.");
			page.fill("//input[@id='websitename']", "https://snj.com");
			page.fill("//input[@id='inputCity']", "New York");
			page.fill("//input[@id='inputAddress1']", "123 Main St");
			page.fill("//input[@id='inputAddress2']", "Suite 100");
			page.fill("//input[@id='inputState']", "New York");
			page.fill("//input[@id='inputZip']", "10001");
			page.selectOption("select[name='country']", new SelectOption().setLabel("United States"));
			page.click("//button[normalize-space()='Submit']");
			String successMsg = page.locator("//p[@class='success-msg hidden']").textContent();
			Assert.assertEquals(successMsg, "Thanks for contacting us, we will get back to you shortly.",
					"Success message not displayed as expected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Test ID: CEUGV-ZGM3S-ZLRJS-RZ9S6

	@AfterMethod()
	public void tearDown() {
		browser.close();
	}
}
