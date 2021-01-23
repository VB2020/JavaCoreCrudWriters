package com.VB2020.service.impl;


import com.VB2020.model.Label;
import com.VB2020.repository.LabelRepository;
import com.VB2020.repository.PostRepository;
import com.VB2020.service.LabelService;

import java.util.List;

public class JavaIOLabelServiceImpl implements LabelService {

    private LabelRepository labelRepo;
    private PostRepository postRepository;


    public JavaIOLabelServiceImpl(LabelRepository labelRepo, PostRepository postRepository) {
        this.labelRepo = labelRepo;
        this.postRepository = postRepository;
    }

    @Override
    public Label getById(Long id) throws Exception {
        return labelRepo.getById(id);
    }

    @Override
    public void create(String name) throws Exception {
        Label label = new Label();
        // автоинкремент
        label.setId(labelRepo.getLastId() + 1);
        label.setName(name);
        labelRepo.save(label);
    }

    @Override
    public void delete(Long id) throws Exception {
        Label label = getById(id);
        if (postRepository.isContainLabel(label)) {
            String cannotDeleteLabelMessage = "Can't delete label, because it wired with post!";
            throw new Exception(cannotDeleteLabelMessage);
        }
        labelRepo.delete(label);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        labelRepo.update(label);
    }

    @Override
    public List<Label> getAll() throws Exception {
        return labelRepo.getAll();
    }
}
