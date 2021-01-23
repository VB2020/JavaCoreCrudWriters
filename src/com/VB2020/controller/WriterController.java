package com.VB2020.controller;


import com.VB2020.model.Writer;
import com.VB2020.service.WriterService;

import java.util.List;

public class WriterController {

    WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    public List<Writer> getAll() throws Exception {

        return writerService.getAll();
    }

    public Writer getById(Long id) throws Exception {

        return writerService.getById(id);
    }

    public void create(String firstName, String lastName) throws Exception {

        writerService.create(firstName, lastName);
    }

    public void update(Long id, String firstName, String lastName) throws Exception {

        writerService.update(id, firstName, lastName);
    }

    public void delete(Long id) throws Exception {

        writerService.delete(id);
    }
}
