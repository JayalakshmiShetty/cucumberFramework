package Steps;

import PageClass.HomePage;
import PageClass.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPageStep extends LoginPage {
	

	@And("I login with email {string} and password {string}")
	public void iLoginWithEmailAndPassword(String arg0, String arg1) throws InterruptedException {
		loginTocoinMarketCap(arg0, arg1);
	}


}
