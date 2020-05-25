package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.Random;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MessagePatientPage {
	WebDriver driver;
	Random rand= new Random();
	
	HelperClass helperObj;
	By subject=By.id("subject");
	By message=By.id("message");
	By sendB=By.xpath("//input[@value='Send']");
	
	public MessagePatientPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	
	//navigate to message
	
	
	
	public HashMap<String,String> inputMessage()
	{
		HashMap<String, String> hMap=new HashMap<String, String>();
		
		String subjectValue="Backpain"+rand.nextInt(100);
		driver.findElement(subject).sendKeys(subjectValue);
		
		String messageValue="Backpain for 3 days"+rand.nextInt(100);
		driver.findElement(message).sendKeys(messageValue);
		
		hMap.put("Subject", subjectValue);
		hMap.put("Message", messageValue);
		return hMap;
	}
	
	public void clickSend()
	{
		driver.findElement(sendB).click();
	}
	
	
	
	public String readSuccessMessage()
	{
		Alert aler=driver.switchTo().alert();
		aler.getText();
		String text=aler.getText();
		aler.accept();
		return text;
		
	}
	
	public boolean validateMessageAdminPortal(HashMap<String,String> hMap)
	{
		boolean result=false;
		helperObj=new HelperClass(driver);
		helperObj.moduleNavigation("Message");
		if(hMap.get("Subject").equals(driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[2]")).getText())
				&& hMap.get("Message").equals(driver.findElement(By.xpath("//table[@class='table']/tbody/tr[3]/td[2]")).getText()))
		{
	    	   result=true;
	       }
			
			return result;
		
	}

}
