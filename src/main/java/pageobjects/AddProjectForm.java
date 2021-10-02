package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {
    private final ILabel lblSuccessAlert = getElementFactory().getLabel(By.xpath("//form[@id='addProjectForm']//div[contains(@class,'alert-success')]"), "Success alert");
    private final IButton btnSaveProject = getElementFactory().getButton(By.xpath("//form[@id='addProjectForm']//button[contains(@class,'btn-primary')]"),"Save project btn");
    private final ITextBox txbProjectName = getElementFactory().getTextBox(By.id("projectName"),"Project name input");

    public AddProjectForm() {
        super(By.id("addProject"), "Add project form");
    }

    public boolean isDisplayedAlert(){
        return lblSuccessAlert.state().waitForDisplayed();
    }

    public boolean isNotDisplayed(){
        return this.state().waitForNotDisplayed();
    }

    public void typeAndSaveProjectName(String name){
        txbProjectName.clearAndType(name);
        btnSaveProject.click();
    }
}
