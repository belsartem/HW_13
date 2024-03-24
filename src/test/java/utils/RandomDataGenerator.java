package utils;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDataGenerator {

    static Faker faker = new Faker();

    public static int getRandomInt(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomGender() {

        return faker.options().option("Male",
                "Female",
                "Other");
    }

    public static String getRandomDay() {

        return String.valueOf(getRandomInt(1, 28));
    }

    public static String getRandomMonth() {

        return faker.options().option("January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December");
    }

    public static String getRandomYear() {

        return String.valueOf(getRandomInt(1924, 2024));
    }

    public static String getRandomSubject() {

        return faker.options().option("Maths",
                "History",
                "English",
                "Arts",
                "Computer Science",
                "Physics");
    }

    public static String getRandomHobbie() {

        return faker.options().option("Sports",
                "Reading",
                "Music");
    }

    public static String getRandomState() {

        return faker.options().option("NCR",
                "Uttar Pradesh",
                "Haryana",
                "Rajasthan");
    }

    public static String getRandomCity(String state){

        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> state;
        };
    }

    //Можно использовать как еще один из способов генерации
/*    public static String getRandomPhone() {
        return String.format("%s %s %s %s %s", getRandomInt(1, 9), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }*/
}