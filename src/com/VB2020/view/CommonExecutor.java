package com.VB2020.view;

import com.VB2020.controller.*;
import com.VB2020.model.ForConsole;
import com.VB2020.repository.*;
import com.VB2020.repository.IO_implementations.IOLabelRepositoryImpl;
import com.VB2020.repository.IO_implementations.IOPostRepositoryImpl;
import com.VB2020.repository.IO_implementations.IOWriterRepositoryImpl;
import com.VB2020.service.LabelService;
import com.VB2020.service.PostService;
import com.VB2020.service.WriterService;
import com.VB2020.service.impl.JavaIOLabelServiceImpl;
import com.VB2020.service.impl.JavaIOPostServiceImpl;
import com.VB2020.service.impl.JavaIOWriterServiceImpl;

import java.util.Scanner;

public class CommonExecutor {

    CommonView labelView;
    CommonView postView;
    CommonView writerView;


    private Scanner sc = new Scanner(System.in);

    public CommonExecutor(){
        try {
            //create repo
            LabelRepository labelRepo = new IOLabelRepositoryImpl();
            WriterRepository writerRepo = new IOWriterRepositoryImpl();
            PostRepository postRepo = new IOPostRepositoryImpl(labelRepo, writerRepo);

            //create services
            WriterService writerService = new JavaIOWriterServiceImpl(writerRepo, postRepo);
            LabelService labelService = new JavaIOLabelServiceImpl(labelRepo, postRepo);
            PostService postService = new JavaIOPostServiceImpl(postRepo);

            //create controllers
            PostController postController = new PostController(postService);
            LabelController labelController = new LabelController(labelService);
            WriterController writerController = new WriterController(writerService);

            //create views
            labelView = new JavaIOLabelViewImpl(labelController, sc);
            writerView = new JavaIOWriterViewImpl(writerController, sc);
            postView = new JavaIOPostViewImpl(postController, sc, writerView, labelView);
        }
        catch (Exception ex)
        {
            String damagedDataMessage = "Damaged data!";
            System.out.println(damagedDataMessage);
        }
    }

    public void run()  {
        boolean isExit = false;
        do {
            System.out.println(ForConsole.BORDER.getMessage());
            String menuMessage = "Choose an action with:\n" +
                    "1. Labels\n" +
                    "2. Writers\n" +
                    "3. Posts\n" +
                    "4. Exit program";
            System.out.println(menuMessage);
            System.out.println(ForConsole.BORDER.getMessage());
            String response = sc.next();
            switch (response) {
                case "1":
                    labelView.show();
                    break;
                case "2":
                    writerView.show();
                    break;
                case "3":
                    postView.show();
                    break;
                case "4":
                    isExit = true;
                    break;
                default:
                    System.out.println(ForConsole.WRONG_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
        sc.close();
    }
}
