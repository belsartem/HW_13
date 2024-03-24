package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;
import static utils.RandomDataGenerator.*;

public class StudentRegistrationFormTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String randomFirstName = faker.name().firstName(),
            randomLastName = faker.name().lastName(),
            randomEmail = faker.internet().emailAddress(),
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
    void testCompleteRegistrationForm() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Landing Page", () -> {
            registrationPage
                    .openPage();
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
        });

        step("Click Submit button", () -> {
            registrationPage
                    .clickSubmit();
        });

        step("Checking Result Window appears", () -> {
            registrationPage
                    .checkResultWindowAppear();
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
        });

/*        registrationPage
                .openPage()
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
                .setCity(randomCity)
                .clickSubmit()
                .checkResultWindowAppear();*/

/*        registrationPage.checkResultModalWindowComponent("Student Name", randomFirstName + " " + randomLastName)
                .checkResultModalWindowComponent("Student Email", randomEmail)
                .checkResultModalWindowComponent("Gender", randomGender)
                .checkResultModalWindowComponent("Mobile", randomPhoneNumber)
                .checkResultModalWindowComponent("Date of Birth", randomDay + " " + randomMonth + "," + randomYear)
                .checkResultModalWindowComponent("Subjects", randomSubjects)
                .checkResultModalWindowComponent("Hobbies", randomHobbies)
                .checkResultModalWindowComponent("Picture", "Designer.png")
                .checkResultModalWindowComponent("Address", randomAddress)
                .checkResultModalWindowComponent("State and City", randomState + " " + randomCity);*/
    }

    @Test
    void testOnlyRequiredRegistrationForm() {

        registrationPage
                .openPage()
                .setFirstName(randomFirstName)
                .setLastName(randomLastName)
                .setGender(randomGender)
                .setPhone(randomPhoneNumber)
                .clickSubmit()
                .checkResultWindowAppear();

        registrationPage.checkResultModalWindowComponent("Student Name", randomFirstName + " " + randomLastName)
                .checkResultModalWindowComponent("Gender", randomGender)
                .checkResultModalWindowComponent("Mobile", randomPhoneNumber);
    }

    @Test
    void testEmptyFields() {

        registrationPage
                .openPage()
                .clickSubmit()
                .checkResultWindowAbsent();
    }

    @Test
    void testNoNameAndLastName() {

        registrationPage
                .openPage()
                .setGender(randomGender)
                .setPhone(randomPhoneNumber)
                .clickSubmit()
                .checkResultWindowAbsent();
    }
}