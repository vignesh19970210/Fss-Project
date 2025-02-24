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

/**
 * 
 * @author Vignesh.T
 * @see AdminPortal TestCase
 * @throws Exception
 * 
 */

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class ProcessorAdmin_ViewInstitution_Testcase extends ProjectObjects {
	LoginPage login = ApplicationLauncher.getLoginInstance();

	public ProcessorAdmin_ViewInstitution_Testcase() {
		MiscMethods.ATUReportGeneration();
		try {
			Configprop = FileUtility.loadObjectRepository("./properties//Config.properties");
			prop = FileUtility.loadObjectRepository("./properties//Admin_ProcessorAdmin_ViewInstitution.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "Verify the search and results functionality in the AddUpdateUser module. Test ID: BOB=TC078-TC079 | DIB=TC178-TC187 | BaseBuild=", groups = {
			"BOB", "DIB", "BaseBuild" }, dataProvider = "ProcessorAdmin_View", enabled = true, priority = 1)
	public void ViewInstitution(HashMap testdata) throws Exception {
		login.loginAsAdmin().ClickAdministrationMenuss((String) testdata.get("MainMenu"))
				.ClickandMouseoverProcessorUserMenu((String) testdata.get("Menu"), (String) testdata.get("SubMenu"))
				.ClickViewInstitutionName(Configprop.getProperty("Bank"))
				// Validating Institution name menu
				.ClickInstitutionDetails()
				.AssertViewInstitution(prop.getProperty("ViewInstitution.InstitutionDetails.InstitutionName.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.InstitutionName.Text"))
				// Validating System Admin Menu
				.ClickSystemAdmin()
				.AssertViewInstitution(
						prop.getProperty("ViewInstitution.InstitutionDetails.SystemAdmin.InstitutionCountry.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.SystemAdmin.InstitutionCountry.Text"))
				// Validating User Admin Menu
				.ClickUserAdmin()
				.AssertViewInstitutionUserAdmin(
						prop.getProperty("ViewInstitution.InstitutionDetails.UserAdmin.PasswordLengthDIB.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.UserAdmin.PasswordLengthDIB.Text"),
						Configprop.getProperty("Bank"),
						prop.getProperty("ViewInstitution.InstitutionDetails.UserAdmin.PasswordLengthBOB.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.UserAdmin.PasswordLengthBOB.Text"))
				// Validating Merchant Maintenance menu
				.ClickMerchantMaintenance()
				.AssertViewInstitution(
						prop.getProperty(
								"ViewInstitution.InstitutionDetails.MerchantMaintenance.T+NOPTIONINDAYS.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.MerchantMaintenance.T+NOPTIONINDAYS.Text"))
				// Validating Interchange Scheme SetUp menu
				.ClickInterchange_SchemeSetup()
				.AssertViewInstitution(prop.getProperty(
						"ViewInstitution.InstitutionDetails.InterchangeSchemeSetup.VISAACQUIRERBUSINESSID.Xpath"),
						prop.getProperty(
								"ViewInstitution.InstitutionDetails.InterchangeSchemeSetup.VISAACQUIRERBUSINESSID.Text"))
				// Validating Payments menu
				.ClickPayments(Configprop.getProperty("Bank"))
				.AssertViewInstitutionUserAdmin(
						prop.getProperty("ViewInstitution.InstitutionDetails.Payments.AcquirerCorporateNumber.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.Payments.AcquirerCorporateNumber.Text"),
						Configprop.getProperty("Bank"),
						prop.getProperty("ViewInstitution.InstitutionDetails.Payments.DeclinedCardRetention.Xpath"),
						prop.getProperty("ViewInstitution.InstitutionDetails.Payments.DeclinedCardRetention.text"))
				.BackButton().HomeButton();
		new LoginPage().clickLogoutSetdriver();

	}

	@DataProvider(name = "ProcessorAdmin_View")
	public static Object[][] getDataFromDataProviderAddUser() {
		Object[][] excelData = ExcelUtility.getDataFromExcel("Admin_TestData.xls", "ProcessorAdmin_View",
				"ViewInstitution");

		System.out.println("Datasheet");
		Object[][] objTemp = ExcelUtility.returnDataProvider(excelData);
		return objTemp;
	}

}
