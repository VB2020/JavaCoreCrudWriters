package com.VB2020.view;


import com.VB2020.controller.LabelController;
import com.VB2020.model.Entity;
import com.VB2020.model.Label;
import com.VB2020.model.ForConsole;

import java.util.*;

public class JavaIOLabelViewImpl extends CommonView {

    LabelController labelController;

    private final String createMessage = "Create label.\n" +
            ForConsole.NAME.getMessage();

    private final String editMessage = "Edit label.\n" +
            ForConsole.ID.getMessage();

    private final String deleteMessage = "Delete label\n" +
            ForConsole.ID.getMessage();


    public JavaIOLabelViewImpl(LabelController labelController, Scanner sc) {
        this.labelController = labelController;
        this.sc = sc;
        String mainMessage = "Choose an action with labels:\n" +
                " 1. Create\n" +
                " 2. Edit\n" +
                " 3. Delete\n" +
                " 4. Show all labels\n" +
                " 5. Exit";
        this.message = mainMessage;
    }

    public void create()
    {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(createMessage);
        String name = sc.next();
        try {
            labelController.create(name);
            System.out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public void edit()
    {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(editMessage);
        Long id = sc.nextLong();
        System.out.println(ForConsole.NAME.getMessage());
        String name = sc.next();
        try {
            labelController.update(id, name);
            System.out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }

        System.out.println(ForConsole.BORDER.getMessage());
    }

    public void delete()
    {
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println(deleteMessage);
        Long id = sc.nextLong();
        try {
            labelController.delete(id);
            System.out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(ForConsole.NOT_DONE.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public void print()
    {
        List<Label> labels;
        try {
            labels = labelController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(ForConsole.BORDER.getMessage());
        String printMessage = "List of labels:\n" +
                "ID; NAME";
        System.out.println(printMessage);
        labels.sort(Comparator.comparing(Entity::getId));
        if (labels.size() != 0) {
            for (Label c : labels) {
                System.out.println(c.getId() + "; " + c.getName());
            }
        }
        else
        {
            System.out.println(ForConsole.EMPTY_LIST.getMessage());
        }
        System.out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    void under_review() {

    }
}
