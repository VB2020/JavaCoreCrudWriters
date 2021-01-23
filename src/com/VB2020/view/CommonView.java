package com.VB2020.view;

import com.VB2020.model.ForConsole;

import java.util.Scanner;

public abstract class CommonView {

    protected String message;
    protected Scanner sc;

    abstract void create();

    abstract void edit();

    abstract void delete();

    abstract void print();

    abstract void under_review();

    void show(){
        boolean isExit = false;
        do {
            print();
            System.out.println(ForConsole.BORDER.getMessage());
            System.out.println(message);
            System.out.println(ForConsole.BORDER.getMessage());
            String response = sc.next();
            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println(ForConsole.WRONG_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
    }
}
