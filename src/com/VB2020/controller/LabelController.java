package com.VB2020.controller;


import com.VB2020.model.Label;
import com.VB2020.service.LabelService;

import java.util.List;

public class LabelController {

    LabelService labelService;

    public LabelController(LabelService labelService) {

        this.labelService = labelService;
    }

    public List<Label> getAll() throws Exception {

        return labelService.getAll();
    }

    public Label getById(Long id) throws Exception {

        return labelService.getById(id);
    }

    public void create(String name) throws Exception {

        labelService.create(name);
    }

    public void update(Long id, String name) throws Exception {

        labelService.update(id, name);
    }

    public void delete(Long id) throws Exception {

        labelService.delete(id);
    }
}
