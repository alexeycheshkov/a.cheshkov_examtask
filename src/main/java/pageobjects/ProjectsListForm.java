package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsListForm extends Form {
    private final String projectXPath = "//a[contains(text(),'%s')]";
    private IButton btnProject;
    private final IButton btnAddProject = getElementFactory().getButton(By.xpath("//button[contains(@data-target,'addProject')]"), "Add project btn");

    public ProjectsListForm() {
        super(By.xpath("//button[contains(@data-target,'addProject')]//ancestor::div[contains(@class,'panel-default')]"), "Projects table");
    }

    public void clickProject(String projectName) {
        btnProject = getElementFactory().getButton(By.xpath(String.format(projectXPath, projectName)), projectName + " project button");
        btnProject.click();
    }

    public boolean isDisplayedProject(String projectName){
        btnProject = getElementFactory().getButton(By.xpath(String.format(projectXPath, projectName)), projectName + " project button");
        return btnProject.state().waitForDisplayed();
    }

    public void clickAddProjectBtn(){
        btnAddProject.click();
    }
}
