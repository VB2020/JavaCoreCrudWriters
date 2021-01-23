package com.VB2020.model;

public enum ForConsole {

    BORDER("//////////////////////////////////////"),
    WRONG_INPUT("Wrong input. Repeat once more!"),
    EMPTY_LIST("List is empty"),
    DONE("Successful operation!"),
    NOT_DONE("Error!"),
    FIRSTNAME("Input first name:"),
    LASTNAME("Input last name:"),
    CONTENT("Input content of this post:"),
    NAME("Input name of this label:"),
    ID("Input ID:"),
    NOT_FIND_ID("This ID doesn't exist = ");

    private final String message;

     ForConsole(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
