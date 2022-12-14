package org.allJavaFiles.MainUI;

import org.allJavaFiles.Data.DataManage;
import org.allJavaFiles.Mechanisms.Logger;
import org.allJavaFiles.Mechanisms.Mechanisms;

import java.util.Objects;

import static org.allJavaFiles.MainUI.MainInterface.BACK;
import static org.allJavaFiles.MainUI.MainInterface.SCANNER;

public enum CustomerInterface implements UserInterface {
    CUSTOMER_INTERFACE {
        @Override
        public UserInterface action() {
            System.out.println("""
                    1. Rent a car
                    2. Return a rented car
                    3. My rented car
                    0. Back""");

            return switch (SCANNER.next().trim()) {
                case "1" -> RENT_CAR.action();
                case "2" -> RETURN_CAR.action();
                case "3" -> MY_CAR.action();
                case "0" -> BACK.action();
                default -> ERROR.action();
            };
        }
    },
    RENT_CAR {
        @Override
        public UserInterface action() {
            if (! Objects.equals(DataManage.rentedCarID(), null)) {

                System.out.println("You've already rented a car!");
                return CUSTOMER_INTERFACE.action();

            } else return Mechanisms.getCompanyList();
        }
    },
    RETURN_CAR {
        @Override
        public UserInterface action() {
            System.out.println(! Objects.equals(DataManage.rentedCarID(), null) ?
                    "You've returned a rented car!" : "You didn't rent a car!");

            DataManage.returnCar();
            Logger.setCar(null);

            return CUSTOMER_INTERFACE.action();
        }
    },
    MY_CAR {
        @Override
        public UserInterface action() {

            if (! Objects.equals(DataManage.rentedCarID(), null)) {
                System.out.printf("""
                        Your rented car:
                        %s
                        Company:
                        %s
                        """, Logger.getCar().getName(), Logger.getCompany().getName());
            } else {
                System.out.println("You didn't rent a car!");
            }

            return CUSTOMER_INTERFACE.action();
        }
    },
    ERROR {
        @Override
        public UserInterface action() {
            System.out.println("Wrong num of action!");
            return CUSTOMER_INTERFACE.action();
        }
    }

}
