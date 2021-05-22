package PageClass;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Steps.Hook;
import util.TestUtil;

import java.util.*;

public class HomePage {
	WebDriverWait wait;
	public static WebDriver driver;
	

	public HomePage() {
		HomePage.driver = Hook.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Hook.ExplicitWaitTimeOutUnit);
	}
	

	@FindBy(xpath = "//div[contains(@class,'desktop')]//button[text()='Log In']")
	WebElement btnHomePageLogin;

	@FindBy(xpath = "//div[contains(@class,'page-sizer')]/div")
	WebElement viewResultDD;

	@FindBy(xpath = "//nav//li/a//*[text()='Cryptocurrencies']")
	WebElement criptocurrenciesTab;

	@FindBy(xpath ="//nav//a[@href='/watchlist/']")
	WebElement watchlistTab;

	@FindBy(xpath ="//*[text()='Filters']/parent::div/button[1]")
	WebElement btnFilters;

	@FindBy(xpath ="//button[text()='Industry']")
	WebElement industryDD;

	@FindBy(xpath ="//input[@placeholder='Search']")
	WebElement txtboxSearch;

	@FindBy(xpath ="//*[text()='Popular Industries']/parent::div/ul/li[1]")
	WebElement industryOption;

	public void LaunchCoinMarketCap() {
		driver.get("https://coinmarketcap.com/");
	}


    public void selectCriptoCurrencies(int listStart, int listEnd) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']/..")));
        List<WebElement> starIcons=driver.findElements(By.xpath("//tbody/tr/td[1]/span"));
			List<Integer> list = new ArrayList<>();
			while(list.size()<6){
				int rndm=getRandomIndex(listStart, listEnd);
				if(!list.contains(rndm)){
				list.add(rndm);
            if(!starIcons.get(rndm).getAttribute("class").contains("is-starred")){
            	scrollToElement(starIcons.get(rndm-1));
			TestUtil.clickElement(starIcons.get(rndm));}
        }System.out.println(list);} }

	public int getRandomIndex(int listStart, int listEnd)
	{
		// create a list of Integer type
		List<Integer> list = new ArrayList<>();
		// add 5 indexes in ArrayList
		for (int i=listStart-1;i<listEnd;i++){
			list.add(i);
		}
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	public boolean verifyThatCriptocurrenciesAreSelected(int fromId, int toId) {
		boolean flag = false;
		for (int i = fromId; i <=toId; i++) {
			WebElement el=driver.findElement(By.xpath("//tbody/tr/td[2]/p[text()="+ i + "]"));
			if(el.isDisplayed()){
			flag=true;
			}
			else{
				flag=false;
				break;
			}}
		return flag;}


		public void scrollToElement(WebElement el){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
	}
	public static void openNewTab()
	{
		((JavascriptExecutor) driver).
				executeScript("window.open()");}
	public static void switchToTab(int t)
	{

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().

				window(tabs.get(t));
	}

	public void selectNumberofRows(int n) throws InterruptedException {
		TestUtil.clickElement(viewResultDD);
		driver.findElement(By.xpath("//button[text()="+n+"]")).click();
	}

   public int getNoOfResultsOnPage(){
	List<WebElement> results= driver.findElements(By.xpath("//tbody/tr"));
	int resultCount=results.size();
	return resultCount;
   }

   public boolean verifyThatNoOfResultDisplayedEqualsToSelectedNumber(int n){
		if(getNoOfResultsOnPage()==n) {
			return true;
		}
		else
		{
			return false;
		}
   }

	public void clickOnWatchlistTab() throws InterruptedException {
		TestUtil.clickElement(watchlistTab);
	}

	public void clickOnHomepageLogin() throws InterruptedException {
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'desktop')]//button[text()='Log In']")));
		TestUtil.clickElement(btnHomePageLogin);
	}

	public void mouseHoverOnCriptoCurrenciesTab(){
		//Creating object of an Actions class
		Actions action = new Actions(driver);
		//Performing the mouse hover action on the target element.
		action.moveToElement(criptocurrenciesTab).perform();
	}

	public void clickOnRankingFromList(String text){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tippy-box']//*[text()='Ranking']/ancestor::a[1]")));
		driver.findElement(By.xpath("//div[@class='tippy-box']//*[text()='Ranking']/ancestor::a[1]")).click();

	}


	public Map<Integer, String[]> recordTheData(){
		Map<Integer, String[]> prices = new HashMap<Integer, String[]>();
		List<WebElement> rows= driver.findElements(By.xpath("//tbody/tr"));
		System.out.println(rows.size());
		for(int row=1;row<=rows.size();row++)
		{
			WebElement id=driver.findElement(By.xpath("//tbody/tr["+ row + "]/td[2]"));
			if(Integer.parseInt(id.getText())<=100) {
				List<WebElement> links = driver.findElements(By.xpath("//tbody/tr[" + row + "]/td/div/a"));
				List<WebElement> texts = driver.findElements(By.xpath("//tbody/tr[" + row + "]/td/p"));
				List<WebElement> percentages = driver.findElements(By.xpath("//tbody/tr[" + row + "]/td/span"));
				WebElement circulatingSuply = driver.findElement(By.xpath("//tbody/tr[1]/td/div/div/p"));
				scrollToElement(links.get(0));
				prices.put(Integer.parseInt(id.getText()), new String[]{links.get(0).getText(), links.get(1).getText(), percentages.get(0).getText(), percentages.get(1).getText(), texts.get(1).getText(), links.get(2).getText(), circulatingSuply.getText()});
				System.out.println(row + "st row values are" + Arrays.toString(prices.get(Integer.parseInt(id.getText()))));}
			else
				{break;}

		}
		scrollToElement(btnFilters);
	return prices;}


	public void clickOnFiltersButton() throws InterruptedException {
		TestUtil.clickElement(btnFilters);
	}

	public void filterByIndustry(String s) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Industry']")));
		TestUtil.clickElement(industryDD);
		TestUtil.enterText(txtboxSearch, s);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Your keyword", Keys.ENTER);
		//TestUtil.clickElement(industryOption);
		recordTheData();
	}
	public void filterWithMoreFilters(String[] s) throws InterruptedException {

	}

	public void compareTheRankingResultsWithFilterResult(Map<Integer, String[]> rankingData, Map<Integer, String[]> dataByIndustryFilter){
//		Set rankingKeys = rankingData.keySet();
//		Set filterKeys = dataByIndustryFilter.keySet();
//		Collection<String[]> rankingValues = dataByIndustryFilter.values();
//		Collection<String[]> filterValues=dataByIndustryFilter.values();
//		List<String> listRanking = new ArrayList<>(rankingKeys);
//		List<String> listFilter = new ArrayList<>(filterKeys);
//		rankingData.forEach((k, v) -> System.out.println(k+" "+v));
//		dataByIndustryFilter.forEach((k, v) -> System.out.println(k+" "+v));

		Map<Integer, String[]> mapC = new HashMap<>(rankingData);

		
		mapC.keySet().retainAll(dataByIndustryFilter.keySet());
		mapC.forEach((k, v) -> System.out.println(k+" "+Arrays.toString(v)));
		Map<Integer, String[]> mapD = new HashMap<>(dataByIndustryFilter);
		mapD.keySet().retainAll(dataByIndustryFilter.keySet());
		mapD.forEach((k, v) -> System.out.println(k+" "+Arrays.toString(v)));
//		System.out.println("Ranking page keys are"+rankingKeys);
//		System.out.println("Filter page keys are"+filterKeys);

//
//		Iterator i = filterKeys.iterator();
//		while (i.hasNext()) {
//			System.out.println(i.next());
//			if(rankingKeys.contains(filterKeys)){
//				System.out.println(Arrays.toString(rankingData.get(i.next())));
//				System.out.println(Arrays.toString(dataByIndustryFilter.get(i.next())));
//			}
//		}


	}
	}
