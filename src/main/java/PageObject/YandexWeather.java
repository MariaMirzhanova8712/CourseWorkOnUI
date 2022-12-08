package PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
public class YandexWeather {

    private final String url = "https://yandex.ru/pogoda/";
    private final SelenideElement cityInputField = $(".mini-suggest-form__input.mini-suggest__input");
    private final ElementsCollection suggestions = $$(".mini-suggest__item-link");
    private final SelenideElement weatherTenDays = $(By.xpath(
            "//div[@class='forecast-briefly__header-buttons']/a[text() ='Подробный прогноз на 10 дней']"));
    private final ElementsCollection temperatureMorning = $$(By.xpath("//span[contains(text(), 'утром,')]"));
    private final ElementsCollection temperatureDay = $$(By.xpath("//span[contains(text(), 'днём,')]"));
    private final ElementsCollection temperatureEvening = $$(By.xpath("//span[contains(text(), 'вечером,')]"));
    private final ElementsCollection temperatureNight = $$(By.xpath("//span[contains(text(), 'ночью,')]"));
    private final ElementsCollection currentDay = $$(By.xpath(
            "//article[@class='card']/div/h2/span[@class='a11y-hidden']"));
}
