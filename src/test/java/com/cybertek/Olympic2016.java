package com.cybertek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Olympic2016 {

	String url1="https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";
	
	
	
	static WebDriver driver;
		
		@BeforeClass //runs once for all tests
		public void setUp() {
			System.out.println("Setting up WebDriver in BeforeClass...");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//	driver.manage().window().fullscreen();
			driver.get(url1);
			
		}
	
		@Test(priority=1)
		public void Test1() {
			
			List<WebElement> firstColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
			
			ArrayList<Integer> actualList = new ArrayList<Integer>(); 
			for (int i = 0; i < firstColumn.size()-1; i++) {
				actualList.add(Integer.parseInt((firstColumn.get(i).getText())));
			}
			
			SortedSet<Integer> expectedList = new TreeSet<Integer>(actualList);
			Assert.assertEquals(actualList, expectedList);
			
			
			driver.findElement(By.xpath("//th[@class='headerSort'][contains(text(),'NOC')]")).click();
			
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			
			ArrayList<String> actualCountryList = new ArrayList<String>();
			
			
			for (int i = 0; i < countries.size()-1; i++) {
				actualCountryList.add(countries.get(i).getText());
				//System.out.println(actualCountryList);
			}
			System.out.println(actualCountryList+" Arraylist");
			SortedSet<String> expectedCountryList = new TreeSet<String>(actualCountryList);
			System.out.println(expectedCountryList+"Treeset");
			Assert.assertEquals(actualCountryList, expectedCountryList);
			
			List<WebElement> firstColumn1 = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
			ArrayList<Integer> actualList1 = new ArrayList<Integer>(); 
			for (int i = 0; i < firstColumn.size()-1; i++) {
				actualList1.add(Integer.parseInt((firstColumn1.get(i).getText())));
			}
			System.out.println("ex"+expectedList);
			System.out.println("ac"+actualList1);
			Assert.assertFalse(expectedList.equals(actualList1));
				
				
			}
			
		@Test(priority=2)
		public void testCase2() {
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
	
		System.out.println(mostGold());	
		System.out.println(mostSilver());	
		System.out.println(mostBronze());	
		System.out.println(total());
		
		Assert.assertTrue(mostGold().equals("46 - United States")); 
		Assert.assertTrue(mostSilver().equals("37 - United States")); 
		Assert.assertTrue(mostBronze().equals("38 - United States")); 
		Assert.assertTrue(total().equals("121 - United States")); 
		
		
			
		}
		
		@Test(priority=3)
		public void testCase3(){
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			List<String> expectedList=new ArrayList<String>(); 
						 expectedList.add("China");
						 expectedList.add("France");
			Assert.assertEquals(countrybyMedal(18), expectedList); 
			
		}
		
		@Test(priority=4)
		public void testCase4(){
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			String expected ="[6,2]";
			String actual=getIndex("Japan"); 
			Assert.assertEquals(actual, expected);
			
		}
		
		@Test(priority=5)
		public void testCase5(){
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			System.out.println(getSum()); 

			List<String> ls=Arrays.asList("Australia", "Italy"); 
			Assert.assertEquals( getSum(),ls); 
		}
		
		public static List<String> getSum(){
			
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			HashMap<String,Integer> mp= new HashMap<String, Integer>();
			HashMap<String,Integer> hmp= new HashMap<String, Integer>();
			
			SortedSet<String> st= new TreeSet<String>();
			for(int i=0; i<medals.size()-1; i++){
				mp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) );
			}
			for(int i=0; i<medals.size()-1; i++){
				hmp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) ); 
			}
			for(Entry <String,Integer> each: mp.entrySet()){
				for(Entry <String,Integer> other: hmp.entrySet()){
					
					if(!(each.getKey().equals(other.getKey()))&&(each.getValue()+other.getValue())==18){
						st.add(each.getKey());
						st.add(other.getKey());
					} 
					}
				}
				
			 
			
			List<String> ls= new ArrayList<String>(st);
			
			return ls; 
		}
			
			 
		
		public static String getIndex(String country){
			
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			SortedMap<String,Integer> mp= new TreeMap<String, Integer>();
			String str="";
			for(int n=0; n<countries.size()-1; n++){
				mp.put(countries.get(n).getText(),n+1); 
			}
			return "["+mp.get(country)+","+"2]"; 
		}
		
		public static List<String> countrybyMedal(Integer n){
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			SortedMap<Integer, String> mp= new TreeMap<Integer, String>();
			
			Set<String> st= new HashSet<String>();
			for(int i=0; i<medals.size()-1; i++){
				mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
				for (Entry <Integer, String> each : mp.entrySet()) {
					if(each.getKey().equals(n)){
						st.add(each.getValue()); 
					}
				
			}
			}
			List<String> ls= new ArrayList<String>(st);
			
			return ls; 
		}
		
		public static String mostGold(){
			SortedMap<Integer, String> mp= new TreeMap<Integer, String>(); 
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]")); 
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
			
			for(int i=0; i<medals.size()-1; i++){
				mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
				
			}
			
			return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
		}
		public static String mostSilver(){
			SortedMap<Integer, String> mp= new TreeMap<Integer, String>(); 
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]")); 
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
			
			for(int i=0; i<medals.size()-1; i++){
				mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
				
			}
			
			return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
		}
		public static String mostBronze(){
			SortedMap<Integer, String> mp= new TreeMap<Integer, String>(); 
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]")); 
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
			
			for(int i=0; i<medals.size()-1; i++){
				mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
				
			}
			
			return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
		}
		
		public static String total(){
			SortedMap<Integer, String> mp= new TreeMap<Integer, String>(); 
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]")); 
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
			
			for(int i=0; i<medals.size()-1; i++){
				mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
				
			}
			
			return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
		}
		

		}

			
		
		
	
	

