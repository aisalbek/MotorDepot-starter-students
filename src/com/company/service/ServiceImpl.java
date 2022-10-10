package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.*;

import static com.company.Main.*;

public class ServiceImpl implements Service {
    Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);
    LinkedHashSet<Truck> trucks = new LinkedHashSet<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    LinkedHashSet<Driver> drivers = new LinkedHashSet<>(List.of(GSON.fromJson(readDriver(), Driver[].class)));


    public LinkedHashSet<Truck> getTrucks() {
        return trucks;
    }


    public LinkedHashSet<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void changeDriver(String trid) {    //изменить драйвер
        try {
            for (Truck tr : trucks) {
                if (tr.getId().equals(trid)) {
                    if (tr.getState().equals(State.REPAIR)) throw new InputMismatchException();
                    if (tr.getState().equals(State.ROUTE)) throw new RuntimeException();
                    System.out.println("-----------------------------------");
                    System.out.println(tr);
                    System.out.println("выберите водителя : ");
                    System.out.println("------------------------------------");
                    drivers.stream().forEach(System.out::println);
                    String draivid = scanner.nextLine();
                    for (Driver dr : drivers) {
                        if (dr.getIdDiver().equals(draivid)) {
                            for (Truck tr2 : trucks) {
                                if (tr2.getDriver().equals(dr.getName())) {
                                    dr.setTruckName(" ");
                                    tr2.setDriver(" ");
                                }
                                dr.setTruckName(tr.getTruckName());
                                tr.setDriver(dr.getName());
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("трак на ремонте вы не можете поменять водителя");
        } catch (Exception e) {
            System.out.println("трак в пути вы не можете поменят водителя !!!");
        }
        System.out.println("----------------------------------------");
        drivers.stream().forEach(System.out::println);
        System.out.println("----------------------------------------");
    }

    @Override
    public void startDriving(String trid) {//начать вождение
        try {
            for (Truck tr : trucks) {
                if (tr.getId().equals(trid)) {
                    if (!tr.getDriver().equals(" ")) {
                        if (!tr.getDriver().equals(" ")) {
                            if (!tr.getState().equals(State.ROUTE)) {
                                if (tr.getState().equals(State.REPAIR)) {
                                    int a = random.nextInt(1, 3);
                                    if (a == 1) {
                                        tr.setState(State.ROUTE);
                                        System.out.println("трак отправлен в путь");
                                    } else {
                                        tr.setState(State.BASE);
                                        System.out.println("трак отправлен на базу.");
                                    }
                                } else {
                                    tr.setState(State.ROUTE);
                                    System.out.println("трак отправлен в путь");
                                }

                            } else throw new InputMismatchException();
                        } else throw new RuntimeException();
                    } else {
                        tr.setState(State.BASE);
                        System.out.println("трак на базу потомушто нет водителя.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("трак уже в пути вы не можете отправить !!!");
        } catch (Exception e) {
            System.out.println("нет водителя вы не можете отправить путь.");
        }
    }

    @Override
    public void startRepair(String trid) {//начать ремонт
        try {
            for (Truck tr : trucks) {
                if (tr.getId().equals(trid)) {
                    if (!tr.getState().equals(State.REPAIR)) {
                        tr.setState(State.REPAIR);
                        System.out.println("трак отправлен на ремонт.");
                    } else throw new RuntimeException();
                }
            }
        } catch (Exception e) {
            System.out.println("трак уже в ремонте !!!");
        }
    }

    @Override
    public void changeTruckState() {     //изменитьTruckState

    }
}












