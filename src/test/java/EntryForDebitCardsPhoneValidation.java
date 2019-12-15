import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EntryForDebitCardsPhoneValidation {
    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfPhone.csv", numLinesToSkip = 1)
    void shouldWarningIfNotNumbersInPhone(String variantsOfPhone) {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Иван Петров");
        form.$("[data-test-id=phone] input").setValue(variantsOfPhone);
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button_theme_alfa-on-white")).click();
        form.$("[data-test-id=phone]").shouldHave(attribute("class", "input input_type_tel input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white"));
    }
}
