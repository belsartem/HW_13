package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultModalWindowComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public RegistrationPage openPage() {
        String registrationPageURL = "/automation-practice-form";
        open(registrationPageURL);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderPicker = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            chooseHobby = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            chooseState = $("#state"),
            chooseCity = $("#city"),
            submitButton = $("#submit"),
            resultWindow = $(".modal-dialog");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultModalWindowComponent resultModalWindowComponent = new ResultModalWindowComponent();

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderPicker.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPhone(String value) {
        phoneInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String value) {
        chooseHobby.$(byText(value)).shouldNotBe(selected).click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        pictureUpload.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        chooseState.click();
        chooseState.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        chooseCity.click();
        chooseCity.$(byText(value)).click();
        return this;
    }

    public RegistrationPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public void checkResultWindowAppear() {
        resultWindow.should(appear);
    }

    public void checkResultWindowAbsent() {
        resultWindow.shouldNotBe(visible);
    }

    public RegistrationPage checkResultModalWindowComponent(String key, String value) {
        resultModalWindowComponent.checkRegistrationResult(key, value);
        return this;
    }
}