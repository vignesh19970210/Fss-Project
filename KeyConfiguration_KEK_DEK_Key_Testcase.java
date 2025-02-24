package com.fss.adminportal.test.administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fss.common.baseclass.ApplicationLauncher;
import com.fss.common.baseclass.HomePage;
import com.fss.common.baseclass.LoginPage;
import com.fss.common.utils.MiscMethods;
import com.fss.common.utils.ProjectObjects;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import main.ExcelUtility;
import main.FileUtility;

/**
 * 
 * @author Vignesh.T
 * @see AdminPortal TestCase
 * @throws Exception
 * 
 */

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class KeyConfiguration_KEK_DEK_Key_Testcase extends ProjectObjects {
	LoginPage login = ApplicationLauncher.getLoginInstance();

	public KeyConfiguration_KEK_DEK_Key_Testcase() {
		MiscMethods.ATUReportGeneration();
		try {
			Configprop = FileUtility.loadObjectRepository("./properties//Config.properties");
			prop = FileUtility.loadObjectRepository("./properties//Admin_KeyConfiguration_KEK_DEK_Key.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "Verify the search and results functionality in the AddUpdateUser module. Test ID: BOB=TC078-TC079 | DIB= | BaseBuild=", groups = {
			"BOB", "DIB", "BaseBuild" }, dataProvider = "Key_Configuration", enabled = true, priority = 1)
	public void KEKKeyConfiguration(HashMap testdata) throws Exception {
		login.loginAsAdmin()
				.ClickAdministrationKeyConfiguration((String) testdata.get("MainMenu"))
				.ClickandMouseoverKeyConfigurationMenu((String) testdata.get("Menu"), (String) testdata.get("SubMenu"))
				//Verifying the KEKKey screen
				.VerifyKekKeyConfigurationScreen()
				.AssertKeyConfiguration(prop.getProperty("SearchKEKDEK.Header.Xpath"),
						prop.getProperty("SearchKEK.Header.text"))
				.ChooseInstituitonDropDown(Configprop.getProperty("Bank"))
				.AssertInstitutionDropdown(Configprop.getProperty("Bank"))
				//Verifying the Table value and Header
				.VerifyTableHeader(Configprop.getProperty("Bank"))
				.VerifyValueHeaderKEKKey(Configprop.getProperty("Bank")).HomeButton(Configprop.getProperty("Bank"));
		new LoginPage().clickLogoutSetdriver();
	}

	@Test(description = "Verify the search and results functionality in the AddUpdateUser module. Test ID: BOB=TC078-TC079 | DIB= | BaseBuild=", groups = {
			"BOB", "DIB", "BaseBuild" }, dataProvider = "Key_Configuration", enabled = true, priority = 2)

	public void DEKKeyConfiguration(HashMap testdata) throws Exception {
		login.loginAsAdmin()
		.ClickAdministrationKeyConfiguration((String) testdata.get("MainMenu"))
				.ClickandMouseoverKeyConfigurationSubMenu((String) testdata.get("Menu"),
						(String) testdata.get("SubMenu2"))
				//Verifying the DEKKey screen
				.VerifyDekKeyConfigurationScreen()
				.AssertKeyConfiguration(prop.getProperty("SearchKEKDEK.Header.Xpath"),
						prop.getProperty("SearchDEK.Header.text"))
				.ChooseInstituitonDropDown(Configprop.getProperty("Bank"))
				.AssertInstitutionDropdown(Configprop.getProperty("Bank"))
				//Verifying the Table value and Header
				.VerifyTableHeader(Configprop.getProperty("Bank"))
				.VerifyValueHeaderDEKKey(Configprop.getProperty("Bank"));
		new LoginPage().clickLogoutSetdriver();
	}

	@DataProvider(name = "Key_Configuration")
	public static Object[][] getDataFromDataProviderAddUser() {
		Object[][] excelData = ExcelUtility.getDataFromExcel("Admin_TestData.xls", "Key_Configuration",
				"KeyConfiguration");

		System.out.println("Datasheet");
		Object[][] objTemp = ExcelUtility.returnDataProvider(excelData);
		return objTemp;
	}
}
