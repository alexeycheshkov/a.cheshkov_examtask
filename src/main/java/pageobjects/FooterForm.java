package pageobjects;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FooterForm extends Form {
    private final ILabel lblVersion = getElementFactory().getLabel(By.xpath("//footer//p[contains(@class,'footer-text')]/span"), "Version lbl");

    public FooterForm() {
        super(By.xpath("//footer[contains(@class, 'footer')]"), "Footer");
    }

    public String getLblVersion(){
        return lblVersion.getText();
    }
}
