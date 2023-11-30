import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {

    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () ->  { //лямбда выражение - вызов класса метода, который у этого класса все один.
        open("https://github.com/");
        attachment("Source", webdriver().driver().source());
        });
        }




@Test
    public void testAnnotatedAttachments(){
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMainPage();
        steps.takeScreenshot();

}
}
