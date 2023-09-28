package org.example;

import java.io.*;
import java.util.Scanner;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class UserDataApplication {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите данные пользователя в следующем формате:");
            System.out.println("Фамилия Имя Отчество дата рождения номер телефона пол");

            String input = scanner.nextLine();
            String[] inputData = input.split(" ");

            if (inputData.length != 6) {
                throw new InvalidDataFormatException("Не правильный формат. Пожалуйста, укажите все 6 полей.");
            }

            String surname = inputData[0];
            String firstName = inputData[1];
            String patronymic = inputData[2];
            String dob = inputData[3];
            String phoneNumber = inputData[4];
            String gender = inputData[5];


            if (!dob.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new InvalidDataFormatException("Неверный формат даты рождения. Используйте формат дд.мм.гггг.");
            }


            if (!phoneNumber.matches("\\d+")) {
                throw new InvalidDataFormatException("Неправильный формат телефонного номера. Используйте только цифры.");
            }


            if (!gender.matches("[mf]")) {
                throw new InvalidDataFormatException("Неверный пол. Используйте 'm' для мужского пола или 'f' для женского.");
            }


            try (PrintWriter writer = new PrintWriter(new FileWriter(surname + ".txt", true))) {
                writer.println(surname + firstName + patronymic + dob + " " + phoneNumber + gender);
            }

            System.out.println("Данные успешно записаны в файл: " + surname + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка записи в файл.");
        } catch (InvalidDataFormatException e) {
            System.err.println("Неверный ввод: " + e.getMessage());
        }
    }
}
