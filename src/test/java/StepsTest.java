import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;


    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () ->  { //лямбда выражение - вызов класса метода, который у этого класса все один.
        open("https://github.com/");
        });
        //step("Что-то делаем", context -> { - Вызов единственного метода в классе

        step("Ищем репозиторий "+ REPOSITORY, () ->{
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие issues с номером " + ISSUE, ()->{
            $(withText("#"+ ISSUE)).should(Condition.exist);
        });

    }

@Test
    public void testAnnotatedSteps(){
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
}
}
