package com.fss.adminportal.test.administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
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

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class ProcessorAdmin_UpdateInstitution_Testcase extends ProjectObjects {
	LoginPage login = ApplicationLauncher.getLoginInstance();
	public ProcessorAdmin_UpdateInstitution_Testcase() {
		MiscMethods.ATUReportGeneration();
		try {
			Configprop = FileUtility.loadObjectRepository("./properties//Config.properties");
			prop = FileUtility.loadObjectRepository("./properties//Admin_ProcessorAdmin_UpdateInstitution.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "Verify the search and results functionality in the AddUpdateUser module. Test ID: BOB=TC078-TC079 | DIB= | BaseBuild=", groups = {
			"BOB", "DIB", "BaseBuild" }, dataProvider = "ProcessorAdmin_Update", enabled = true, priority = 1)
	public void updateInstitution(HashMap testdata) throws Exception {
		login.loginAsAdmin()
		.ClickAdministrationMenus((String) testdata.get("MainMenu"))
				.ClickandMouseoverProcessorUserMenu((String) testdata.get("Menu"), (String) testdata.get("SubMenu"))
				.VerifyUpdateInstitutionScreen()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.Header.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.Text"))
				.ClickInstitutionName(Configprop.getProperty("Bank")).ClickInstitutionDetails().Verify_error_SuccessMessage_InstitutionDetails()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
				.InstitutionLevelParametersSystemAdmin().Verify_error_SuccessMessage_SystemAdmin()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
			.InstitutionLevelParametersUserAdmin().Verify_error_SuccessMessage_UserAdmin()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
					prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
				.InstitutionLevelParametersMerchantMaintenance().Verify_error_SuccessMessage_Merchant_Maintenance()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
				.InstitutionLevel_Interchange_SchemeSetup().Verify_error_SuccessMessage_Interchange_SchemeSetup()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
				.InstitutionLevel_Payments().Verify_error_SuccessMessage_Payments()
				.AssertSuccessNotification(prop.getProperty("UpdateInstitution.ErrorMessage.Xpath"),
						prop.getProperty("UpdateInstitution.ExpectedViewresult.SuccessMessage.Text"))
			.BackButton().HomeButton();
			new LoginPage().clickLogoutSetdriver();
	}
	
//	@AfterClass (groups = { "BOB", "DIB", "BaseBuild" })
//	public void tearDown() throws Exception {
//		new LoginPage().ClickLogout()
//		.CloseBrowser();
//	}

	@DataProvider(name = "ProcessorAdmin_Update")
	public static Object[][] getDataFromDataProviderAddUser() {
		Object[][] excelData = ExcelUtility.getDataFromExcel("Admin_TestData.xls", "ProcessorAdmin_Update",
				"UpdateInstitution");

		System.out.println("Datasheet");
		Object[][] objTemp = ExcelUtility.returnDataProvider(excelData);
		return objTemp;
	}

}
