package com.VB2020.view;
import com.VB2020.controller.WriterController;
import com.VB2020.model.Entity;
import com.VB2020.model.ForConsole;
import com.VB2020.model.Writer;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class JavaIOWriterViewImpl extends CommonView {

    private WriterController writerController;


    private final String createMessageFIRSTNAME = "Create writer: \n" +
            ForConsole.FIRSTNAME.getMessage();

    private final String createMessageLASTNAME = "Create writer: \n" +
            ForConsole.LASTNAME.getMessage();


    private final String editMessage = "Edit writer.\n" +
            ForConsole.ID.getMessage();

    private final String deleteMessage = "Delete writer\n" +
            ForConsole.ID.getMessage();

    public JavaIOWriterViewImpl(WriterController writerController, Scanner sc)
    {
        this.writerController = writerController;
        this.sc = sc;
        String mainMessage = "Choose an action with writers:\n" +
                " 1. Create\n" +
                " 2. Edit\n" +
                " 3. Delete\n" +
                " 4. Show all writers\n" +
                " 5. Exit";
        this.message = mainMessage;
    }

    @Override
    public void create() {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(createMessageFIRSTNAME);
        String firstName = sc.next();
        System.out.println(createMessageLASTNAME);
        String lastName = sc.next();
        try {
            writerController.create(firstName, lastName);
            System.out.println(ForConsole.DONE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void edit() {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(editMessage);
        Long id = sc.nextLong();
        System.out.println(ForConsole.FIRSTNAME.getMessage());
        String firstName = sc.next();
        System.out.println(ForConsole.LASTNAME.getMessage());
        String lastName = sc.next();
        try {
            writerController.update(id, firstName, lastName);
            System.out.println(ForConsole.DONE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void delete() {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(deleteMessage);
        Long id = sc.nextLong();
        try {
            writerController.delete(id);
            System.out.println(ForConsole.DONE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }

        System.out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void print() {
        List<Writer> writers;
        try {
            writers = writerController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        // Comparator.
        // Compares its two arguments for order.
        // Returns a negative integer,
        // zero, or a positive integer as the first argument is less than,
        // equal to, or greater than the second.
        writers.sort(Comparator.comparing(Entity::getId));
        System.out.println(ForConsole.BORDER.getMessage());
        String printMessage = "List of all writers:\n" +
                "ID; FIRSTNAME; LASTNAME";
        System.out.println(printMessage);
        if (writers.size() != 0)
        {
            for (Writer c : writers)
            {
                System.out.println(c.getId() + "; " + c.getFirstName()+ "; " + c.getLastName());
            }
        } else {
            System.out.println(ForConsole.EMPTY_LIST.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    void under_review() {

    }
}
