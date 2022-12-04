package steps;

import PageObject.YandexWeather;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;

public class YandexWeatherSteps {
    private String town;
    YandexWeather yandexWeather = new YandexWeather();

    @BeforeAll
    public static void setBrowserConfig() {
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        Configuration.timeout = 3000;
    }

    @When("^загрузить сайт ЯндексПогода для города (.*)$")
    public void downloadWebsite(String city) {
        town = city;
        open(yandexWeather.getUrl());
        yandexWeather.getCityInputField()
                .shouldBe(Condition.enabled, Condition.visible)
                .sendKeys(city);
        yandexWeather.getSuggestions().get(0)
                .shouldBe(Condition.enabled, Condition.visible)
                .click();
    }

    @Then("^открыть страницу прогноза погоды на 10 дней$")
    public void openPageWeatherForecast() {
        yandexWeather.getWeatherTenDays()
                .shouldBe(Condition.enabled, Condition.visible)
                .click();
    }
   // вывести в консоль температуру с сегодняшнего дня и \+ (\d+)дней
    @Then("^вывести в консоль температуру на количество дней (\\d+)$")
    public void printTemperatureToConsole(int days) {
        int actualDays;
        if (days > 10){
            actualDays =10;
        } else actualDays=days;
        System.out.println("Погода для города " + town + " на " + actualDays + " дня(ей)");
        System.out.println("--------------------------");

        for (int i = 0; i < actualDays; i++) {

            System.out.println(yandexWeather.getCurrentDay().get(i).getOwnText());
            System.out.println(yandexWeather.getTemperatureMorning().get(i).getOwnText());
            System.out.println(yandexWeather.getTemperatureDay().get(i).getOwnText());
            System.out.println(yandexWeather.getTemperatureEvening().get(i).getOwnText());
            System.out.println(yandexWeather.getTemperatureNight().get(i).getOwnText());
            System.out.println("--------------------------");
        }

    }

    @After
    public static void closeAll() {
        Selenide.closeWebDriver();
    }

}
