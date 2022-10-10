package com.company;

import com.company.entities.Truck;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.ranges.RangeException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ServiceImpl service = new ServiceImpl();
        String number = "null";
        while (!number.equals("x")) {
            try {
                service.getTrucks().stream().forEach(System.out::println);
                System.out.println("выберите трак : ");
                String idtrak = scanner.nextLine();
                namber(idtrak);
                if (Character.isDigit(idtrak.charAt(0))) {
                    buttons();
                    System.out.println("Выберите команду:");
                    number = scanner.nextLine().trim();
                    if (Character.isDigit(number.charAt(0))) {
                        switch (number) {
                            case "1" -> service.changeDriver(idtrak);
                            case "2" -> service.startDriving(idtrak);
                            case "3" -> service.startRepair(idtrak);
                            default -> throw new RuntimeException();
                        }
                    } else {
                        throw new RuntimeException();
                    }
                } else throw new RuntimeException();
            } catch (Exception e) {
                System.out.println("нет такой кнопки !!!");
            }
        }
    }


    public static void buttons() {
        System.out.println("Press 1 to change Driver\n" +
                "Press 2 to send to the Route\n" +
                "Press 3 to send to the Repairing\n");
    }

    public static String namber(String a) {
        if (a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4") || a.equals("5")) {
            return a;
        } else throw new RuntimeException();
    }

    public static String readTtuck() {
        return getString(WRITE_PATH);
    }

    public static String readDriver() {
        return getString(WRITE_PATH1);
    }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))) {
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}