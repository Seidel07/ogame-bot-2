package pages.messages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AllPages;
import pages.generalVision.MenuOptions;

public class MessagesPage extends AllPages{
	
	@FindBy(className = "js_actionKillAll")
	private WebElement deleteAllMessagesButton;
	
	@FindBy(className = "ui-tabs-anchor")
	private List<WebElement> sectionElementList;

	public MessagesPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("ui-id-29"),TimeUnit.SECONDS.toMillis(10));
		}
	}
	
	public void deleteAllMessages() {
		this.goTo(MenuOptions.MESSAGES);
		for (WebElement section : sectionElementList) {
			this.clickOnButton(section);
			if (this.webElementIsDisplayedInPage(By.className("js_actionKillAll"))) {
				this.clickOnButton(this.deleteAllMessagesButton);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Messages deleted");
		this.goTo(MenuOptions.GENERAL_VISION);
	}

}
