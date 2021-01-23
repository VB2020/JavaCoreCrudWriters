package com.VB2020.view;


import com.VB2020.controller.PostController;
import com.VB2020.model.*;

import java.util.*;

import static java.lang.System.*;

public class JavaIOPostViewImpl extends CommonView {

    private PostController postController;
    private CommonView writerView;
    private CommonView labelView;

    private final String editMenuMessage = "Edit post.\n" +
            ForConsole.ID.getMessage();

    private final String deleteMenuMessage = "Delete post.\n" +
            ForConsole.ID.getMessage();

    private final String under_reviewMenuMessage = "Post is under review.\n" +
            ForConsole.ID.getMessage();

    public JavaIOPostViewImpl(PostController postController, Scanner sc, CommonView writerView, CommonView labelView) {
        this.postController = postController;
        this.sc = sc;
        this.labelView = labelView;
        this.writerView = writerView;
    }

    @Override
    public void show() {
        boolean isExit = false;
        do {
            print();
            out.println(ForConsole.BORDER.getMessage());
            String mainMenuMessage = "Choose an action with posts:\n" +
                    " 1. Create post\n" +
                    " 2. Under review post\n" +
                    " 3. Edit post\n" +
                    " 4. Delete post\n" +
                    " 5. Show all posts\n" +
                    " 6. Exit";
            out.println(mainMenuMessage);
            out.println(ForConsole.BORDER.getMessage());

            String response = sc.next();

            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    under_review();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    print();
                    break;
                case "6":
                    isExit = true;
                    break;
                default:
                    out.println(ForConsole.WRONG_INPUT.getMessage());
                    break;
            }

        } while (!isExit);
    }

    @Override
    public void create() {
        out.println(ForConsole.BORDER.getMessage());
        String createMenuMessage = "Create post.";
        out.println(createMenuMessage);
        String content = createPostContent();
        Long writerId = chooseWriter();
        Set<Long> labelIds = chooseLabels();
        try {
            postController.create(content, PostStatus.ACTIVE, writerId, labelIds);
            out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            out.println(ForConsole.NOT_DONE.getMessage());
        }

        out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void edit() throws InputMismatchException {
        out.println(ForConsole.BORDER.getMessage());
        out.println(editMenuMessage);
        Long id = sc.nextLong();
        try
        {
            postController.checkEdit(id);
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            out.println(ForConsole.NOT_DONE.getMessage());
            return;
        }
        String content = createPostContent();
        Long writerId = chooseWriter();
        Set<Long> labelIds = chooseLabels();
        try
        {
            postController.update(id, content, writerId, labelIds);
            out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            out.println(ForConsole.NOT_DONE.getMessage());
        }
        out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void delete() {
        out.println(ForConsole.BORDER.getMessage());
        out.println(deleteMenuMessage);
        Long id = sc.nextLong();
        try
        {
            postController.delete(id);
            out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            out.println(ForConsole.NOT_DONE.getMessage());
        }

        out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void print() {
        List<Post> posts;
        try {
            posts = postController.getAll();
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            return;
        }
        posts.sort(Comparator.comparing(Entity::getId));
        out.println(ForConsole.BORDER.getMessage());
        String printMenuMessage = "List of posts\n" +
                "ID; Content; PostStatus; WriterFirstName; WriterLastName; Labels";
        out.println(printMenuMessage);
        if (posts.size() != 0) {
            for (Post p : posts) {
                String printLine = p.getId() + "; " + p.getContent() + "; " + p.getPostStatus() + "; " + p.getWriter().getFirstName() + "; "+ p.getWriter().getLastName() + "; ";
                StringJoiner joiner = new StringJoiner("/");
                for (Label c : p.getLabels()) {
                    joiner.add(c.getName());
                }
                printLine += joiner.toString();
                out.println(printLine);
            }
        }
        else
        {
            out.println(ForConsole.EMPTY_LIST.getMessage());
        }
        out.println(ForConsole.BORDER.getMessage());
    }

    @Override
    public void under_review() {
        out.println(ForConsole.BORDER.getMessage());
        out.println(under_reviewMenuMessage);
        Long id = sc.nextLong();
        try
        {
            postController.under_review(id);
            out.println(ForConsole.DONE.getMessage());
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
            out.println(ForConsole.NOT_DONE.getMessage());
        }

        out.println(ForConsole.BORDER.getMessage());
    }

    private String createPostContent()
    {
        out.println(ForConsole.BORDER.getMessage());
        out.println(ForConsole.CONTENT.getMessage());
        String content = sc.next();
        out.println(ForConsole.BORDER.getMessage());
        return content;
    }

    private Set<Long> chooseLabels() {

        Set<Long> labelIds = new HashSet<>();
        while (true)
        {
            labelView.print();
            out.println(ForConsole.BORDER.getMessage());
            out.println(ForConsole.ID.getMessage());
            Long labelId = sc.nextLong();
            try {
                postController.checkLabel(labelId);
            }
            catch (Exception e)
            {
                out.println(e.getMessage());
                continue;
            }

            if (labelIds.contains(labelId))
            {
                String addSameLabelMessage = "Label is exist! Try another one!\n" +
                        "ID = ";
                out.println(addSameLabelMessage + labelId);
            }
            else
            {
                labelIds.add(labelId);
            }

            String wantAddLabelMessage = "Do you want to add one more label? (y/n):";
            out.println(wantAddLabelMessage);
            String response = sc.next();
            String answerYes = "y";
            if (!response.equalsIgnoreCase(answerYes)) {
                break;
            }
            out.println(ForConsole.BORDER.getMessage());
        }

        return labelIds;
    }

    private Long chooseWriter() {
        Long writerId;
        while (true)
        {
            writerView.print();
            out.println(ForConsole.BORDER.getMessage());
            out.println(ForConsole.ID.getMessage());
            writerId = sc.nextLong();
            try {
                postController.checkWriter(writerId);
                break;
            }
            catch (Exception e)
            {
                out.println(e.getMessage());
                //continue;
            }
        }
        out.println(ForConsole.BORDER.getMessage());
        return writerId;
    }
}
