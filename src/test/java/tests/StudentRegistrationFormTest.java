package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.ProjectConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.RegistrationPage;

import java.nio.charset.StandardCharsets;

import static io.qameta.allure.Allure.step;
import static utils.RandomDataGenerator.*;

@DisplayName("Registration Form Student Tests")
public class StudentRegistrationFormTest extends BaseTest {

    ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Attachment(value = "Screenshot of the step", type = "image/png", fileExtension = "png")
    public void attachScreenshot() {
        ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    String randomFirstName = projectConfig.firstName(),
            randomLastName = projectConfig.lastName(),
            randomEmail = projectConfig.userEmail(),
            randomGender = getRandomGender(),
            randomPhoneNumber = faker.phoneNumber().subscriberNumber(10),
            randomDay = getRandomDay(),
            randomMonth = getRandomMonth(),
            randomYear = getRandomYear(),
            randomSubjects = getRandomSubject(),
            randomHobbies = getRandomHobbie(),
            randomAddress = faker.address().fullAddress(),
            randomState = getRandomState(),
            randomCity = getRandomCity(randomState);

    @Test
    @DisplayName("Checking Completely Populated Data")
    @Owner("abels")
    @Tag("demoqa")
    void testCompleteRegistrationForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Landing Page", () -> {
            registrationPage
                    .openPage();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Set random data", () -> {
            registrationPage
                    .setFirstName(randomFirstName)
                    .setLastName(randomLastName)
                    .setEmail(randomEmail)
                    .setGender(randomGender)
                    .setPhone(randomPhoneNumber)
                    .setDateOfBirth(randomDay, randomMonth, randomYear)
                    .setSubject(randomSubjects)
                    .setHobby(randomHobbies)
                    .uploadPicture("Designer.png")
                    .setAddress(randomAddress)
                    .setState(randomState)
                    .setCity(randomCity);
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Click Submit button", () -> {
            registrationPage
                    .clickSubmit();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking Result Window appears", () -> {
            registrationPage
                    .checkResultWindowAppear();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking Values In Result Window", () -> {
            registrationPage.checkResultModalWindowComponent("Student Name", randomFirstName + " " + randomLastName)
                    .checkResultModalWindowComponent("Student Email", randomEmail)
                    .checkResultModalWindowComponent("Gender", randomGender)
                    .checkResultModalWindowComponent("Mobile", randomPhoneNumber)
                    .checkResultModalWindowComponent("Date of Birth", randomDay + " " + randomMonth + "," + randomYear)
                    .checkResultModalWindowComponent("Subjects", randomSubjects)
                    .checkResultModalWindowComponent("Hobbies", randomHobbies)
                    .checkResultModalWindowComponent("Picture", "Designer.png")
                    .checkResultModalWindowComponent("Address", randomAddress)
                    .checkResultModalWindowComponent("State and City", randomState + " " + randomCity);
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }

    @Test
    @DisplayName("Checking Only Required Populated Data")
    @Owner("abels")
    @Tag("demoqa")
    void testOnlyRequiredRegistrationForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Landing Page", () -> {
            registrationPage
                    .openPage();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Partially set random data to only required fields", () -> {
            registrationPage
                    .setFirstName(randomFirstName)
                    .setLastName(randomLastName)
                    .setGender(randomGender)
                    .setPhone(randomPhoneNumber);

            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Click Submit button", () -> {
            registrationPage
                    .clickSubmit();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking Result Modal Window is appeared", () -> {
            registrationPage
                    .checkResultWindowAppear();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking results on Modal Window", () -> {
            registrationPage
                    .checkResultModalWindowComponent("Student Name", randomFirstName + " " + randomLastName)
                    .checkResultModalWindowComponent("Gender", randomGender)
                    .checkResultModalWindowComponent("Mobile", randomPhoneNumber);
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }


    @Test
    @DisplayName("Checking Empty Data")
    @Owner("abels")
    @Tag("demoqa")
    void testEmptyFields () {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Landing Page", () -> {
            registrationPage
                    .openPage();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Click Submit button", () -> {
            registrationPage
                    .clickSubmit();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking Result Window Is Not Appeared", () -> {
            registrationPage
                    .checkResultWindowAbsent();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }

    @Test
    @DisplayName("Checking No Name and Last Name Populated Data")
    @Owner("abels")
    @Tag("demoqa")
    void testNoNameAndLastName () {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Landing Page", () -> {
                registrationPage
                        .openPage();
                attachScreenshot();
                Allure.getLifecycle().addAttachment(
                        "Sources of the page",
                        "text/html",
                        "html",
                        WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
                );
            });

        step("Populate only Gender and Phone Number", () -> {
            registrationPage
                    .setGender(randomGender)
                    .setPhone(randomPhoneNumber);
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Click Submit button", () -> {
            registrationPage
                    .clickSubmit();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Checking Result Window Is Not Appeared", () -> {
            registrationPage
                    .checkResultWindowAbsent();
            attachScreenshot();
            Allure.getLifecycle().addAttachment(
                    "Sources of the page",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}