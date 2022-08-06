package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSearch {

    @BeforeAll
    static void configure() {
        Configuration.browser = "chrome";
        Configuration.browserPosition = "0x0";
        Configuration.browserSize = "2560x1440";
    }

    @Test
    void shouldFindselenideSoftassertionsJUnit5() {
        // Открыть страницу github.com
        open("https://github.com/");
        // Ввести в поле поиска selenide и нажать Enter
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        // Проверить, что в заголовке встречается selenide/selenide
        $(".codesearch-results").shouldHave(text("selenide/selenide"));
        // Перейти по первому результату
        $("ul.repo-list li").$("a").click(); //$$("ul.repo-list li").first() - можно оптимизировать на $("ul.repo-list li") - тоже самое, только работает быстрее
        // Перейти в раздел Wiki
        $("#wiki-tab").click();

        // v1 Через ссылку "Soft assertions" на странице
        // Проверить, что встречается "Soft assertions" на  странице
        //$("#wiki-body").shouldHave(text("Soft assertions"));
        // Перейти в раздел "Soft assertions"
        //$("#wiki-body").$("ul li",6).$("a").click();

        // v2 Через ссылку "SoftAssertions" в списке страниц
        // Проверить, что в Pages встречается "Soft assertions"
        $(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        // Перейти в раздел "Soft assertions" в Pages
        //$("#wiki-pages-box").$("ul li",19).$("a").click();
        $(byText("Soft assertions")).click();       // Очень нежелательно привязываться к порядку элемента при выборе локатора. Лучше воспользоваться поиском локатора по тексту.
        // Проверить, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(text("Using JUnit5"));

        // Debug options:
        // sleep (4000);
    }

    @AfterAll
    static void finitaLaCommedia() {
        System.out.println("That's all, Folks!");
    }
}
