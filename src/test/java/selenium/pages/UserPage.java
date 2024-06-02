package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class UserPage extends BasePage {
    @FindBy(css = "[aria-label='Start a new repository'] h4.mb-2")
    private WebElement startNewRepositoryTitle;


    @Step("Verify the user")
    public String getTitleText(){
        waitForElementToBeVisible(startNewRepositoryTitle);
        return startNewRepositoryTitle.getText();
    }



}
