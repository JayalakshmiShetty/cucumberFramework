package Steps;

import PageClass.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class HomePageStep extends HomePage {
	Map<Integer, String[]> dataByIndustryFilter;
	Map<Integer, String[]> rankingsData;

	@Given("I Launch CoinMarketCap")
	public void iLaunchCoinMarketCap() {
		LaunchCoinMarketCap();
	}


	@And("Open Watchlist in new browser")
	public void openWatchlistInNewBrowser() throws InterruptedException {
		clickOnWatchlistTab();
	}

	@Then("Verify that {int} results are displayed")
	public void verifyThatResultsAreDisplayed(int arg0) {
		Assert.assertTrue(verifyThatNoOfResultDisplayedEqualsToSelectedNumber(arg0));
	}

	@When("I choose View {int} rows")
	public void iChooseViewRows(int arg0) throws InterruptedException {
		selectNumberofRows(arg0);
	}

	@When("I click {string} from Cryptocurrencies dropdown")
	public void iClickFromCryptocurrenciesDropdown(String arg0) {
		mouseHoverOnCriptoCurrenciesTab();
		clickOnRankingFromList(arg0);
		rankingsData = recordTheData();
	}

	@And("I Launch CoinMarketCap in new browser")
	public void iLaunchCoinMarketCapInNewBrowser() {
		openNewTab();
		switchToTab(1);
		LaunchCoinMarketCap();
	}
	@When("Add random Criptocurrencies between {int} and {int} to Watchlist")
	public void addRandomCriptocurrenciesBetweenAndToWatchlist(int arg0, int arg1) throws InterruptedException {
		selectCriptoCurrencies(arg0, arg1);
	}


	@And("Verify that all the selected Criptocurrencies  between {int} and {int} are added to the Watchlist")
	public void verifyThatAllTheSelectedCriptocurrenciesBetweenAndAreAddedToTheWatchlist(int arg0, int arg1) {
		Assert.assertTrue(verifyThatCriptocurrenciesAreSelected(arg0, arg1));
	}

	@And("Select Existing Filter Combination For Industry {string}")
	public void selectExistingFilterCombinationForIndustry(String arg0) throws InterruptedException {
		clickOnFiltersButton();
		filterByIndustry(arg0);
		dataByIndustryFilter = recordTheData();
	}

	@Then("Compare the Results with Ranking page")
	public void compareTheResultsWithRankingPage() {
		compareTheRankingResultsWithFilterResult(rankingsData,dataByIndustryFilter);
	}
}
