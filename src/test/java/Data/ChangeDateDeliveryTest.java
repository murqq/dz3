import Data.DataPlaning;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ChangeDateDeliveryTest {


    @Test
    void successfulFormSend() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");

        String planingDate= DataPlaning.generateDate(5,"dd.MM.yyyy");
        String newPlaningDate= DataPlaning.generateDate(6,"dd.MM.yyyy");
        form.$("[data-test-id=city] input").setValue(DataPlaning.generateCity());
        $(".menu").click();
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(planingDate);
        form.$("[data-test-id=name] input").setValue(DataPlaning.generateName());
        form.$("[data-test-id=phone] input").setValue(DataPlaning.generatePhone());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + planingDate));
        $(".icon-button.notification__closer").click();
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(newPlaningDate);
        form.$(".button").click();
        $("[data-test-id=replan-notification] .notification__title").shouldBe(visible).shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + newPlaningDate));



    }
}
