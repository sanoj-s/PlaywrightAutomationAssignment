package com.assigments.base;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SessionManager {
	public Page page;
	public Browser browser;
	public Playwright playwright;
	String lambdaTestUserName = "sanoj.swaminathan";
	String lambdaTestAuthkey = "Kc7tZLfuVmz65AdK0j960CYlusuyaJ8SaKm4tCPdWcVk7dUDDH";

	/**
	 * Method to start the session
	 * 
	 * @author sanoj.swaminathan
	 * @return
	 */
	public Page startSession() {
		try  {
			  playwright = Playwright.create();
			JsonObject capabilities = new JsonObject();
			JsonObject ltOptions = new JsonObject();
			capabilities.addProperty("browsername", "pw-firefox");
			capabilities.addProperty("browserVersion", "latest");
			ltOptions.addProperty("platform", "Windows 10");
//			ltOptions.addProperty("name", "Playwright Test on " + "Chromium");
			ltOptions.addProperty("build", "LambdaTestAutomation");
			ltOptions.addProperty("user", lambdaTestUserName);
			ltOptions.addProperty("accessKey", lambdaTestAuthkey);
			capabilities.add("LT:Options", ltOptions);
			String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
			browser = playwright.chromium().connect(cdpUrl);
			page = browser.newPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
