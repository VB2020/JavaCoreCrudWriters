package com.VB2020.service.impl;


import com.VB2020.model.Writer;
import com.VB2020.repository.PostRepository;
import com.VB2020.repository.WriterRepository;
import com.VB2020.service.WriterService;

import java.util.List;

public class JavaIOWriterServiceImpl implements WriterService {

    private WriterRepository writerRepo;
    private PostRepository postRepository;

    public JavaIOWriterServiceImpl(WriterRepository writerRepo, PostRepository postRepository)
    {
        this.writerRepo = writerRepo;
        this.postRepository = postRepository;
    }

    @Override
    public Writer getById(Long id) throws Exception {
        return writerRepo.getById(id);
    }

    @Override
    public void create(String firstName, String lastName) throws Exception {
        Writer writer = new Writer();

        // автоинкремент
        writer.setId(writerRepo.getLastId() + 1);

        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerRepo.save(writer);
    }

    @Override
    public void delete(Long id) throws Exception {
        Writer writer = getById(id);

        if (postRepository.isContainWriter(writer)) {
            String cannotDeleteWriterMessage = "Can't delete writer, because it wired with post!";
            throw new Exception(cannotDeleteWriterMessage);
        }

        writerRepo.delete(writer);
    }

    @Override
    public void update(Long id, String firstName, String lastName) throws Exception {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerRepo.update(writer);
    }

    @Override
    public List<Writer> getAll() throws Exception {
            return writerRepo.getAll();
    }
}
