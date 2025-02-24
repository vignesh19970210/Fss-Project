package com.fss.adminportal.test.administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fss.adminportal.pages.administration.UserAdmin_AddUpdateUser_Testpage;
import com.fss.common.baseclass.HomePage;
import com.fss.common.baseclass.LoginPage;
import com.fss.common.utils.AssertionMethods;
import com.fss.common.utils.MiscMethods;
import com.fss.common.utils.ProjectObjects;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import main.ExcelUtility;
import main.FileUtility;

/**
 * 
 * @author NaveenKumar
 * @see AdminPortal TestCase
 * @throws Exception
 * 
 */

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class UserAdmin_AddUpdateUser_Testcase extends ProjectObjects {
	public UserAdmin_AddUpdateUser_Testcase() {
		MiscMethods.ATUReportGeneration();
		try {
			Configprop = FileUtility.loadObjectRepository("./properties//Config.properties");
			prop = FileUtility.loadObjectRepository("./properties//Admin_UserAdmin_AddUpdateUser.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "BOB", "DIB", "BaseBuild","BFSL" }, dataProvider = "UserAdmin_AddUpdateUser", enabled = true)
	public void AddUpdateUser(HashMap testdata) throws Exception {
		LoginPage Log = new LoginPage();
		Log.launchAdminURL(Configprop.getProperty("Bank"));
		HomePage Home = Log.loginAsAdmin(Configprop.getProperty("Bank"));
		UserAdmin_AddUpdateUser_Testpage AddUpdateUser = Home.ClickAdministrationMenu((String) testdata.get("MainMenu"));
		AddUpdateUser.ClickandMouseoverAddUpdateUserMenu((String) testdata.get("Menu"),(String) testdata.get("SubMenu"));
		AddUpdateUser.VerifyAddUpdateUserScreen();
		// Validates the assertion for the AddUpdateUser Header screen.
		String ActualHeader = actionsObject.getTextByXpath(prop.getProperty("AddUpdateUser.Header.XPath"));
		String ExpectedHeader = ActualHeader.trim();
		AssertionMethods.softAssertMethod("Verify Header",prop.getProperty("AddUpdateUser.Header.Text"), ExpectedHeader);
		AddUpdateUser.SelectSearchByUsername((String) testdata.get("SearchBy"));
		AddUpdateUser.EnterSearchOn((String) testdata.get("SearchOn"));
		AddUpdateUser.ClickSearchButton();
		AddUpdateUser.ClickResultBackButton();
		AddUpdateUser.ClicktoAddUpdateUserLink();
		AddUpdateUser.ClickAddBack();
		AddUpdateUser.ClickLogout();
		AddUpdateUser.CloseBrowser();
	}
	
	@DataProvider(name = "UserAdmin_AddUpdateUser")
	public static Object[][] getDataFromDataProviderAddUser() {
		Object[][] excelData = ExcelUtility.getDataFromExcel("Admin_TestData.xls", "UserAdmin_AddUpdateUser",
				"AddUser");
		System.out.println("Datasheet");
		Object[][] objTemp = ExcelUtility.returnDataProvider(excelData);
		return objTemp;
	}

}
