package com.VB2020.repository.IO_implementations;


import com.VB2020.model.*;
import com.VB2020.repository.LabelRepository;
import com.VB2020.repository.PostRepository;
import com.VB2020.repository.WriterRepository;
import com.VB2020.operations.IO_operations;

import java.util.*;

public class IOPostRepositoryImpl implements PostRepository {

    private WriterRepository writerRepository;
    private LabelRepository labelRepository;

    private final static String FILE_NAME = "posts.txt";

    public IOPostRepositoryImpl(LabelRepository labelRepository, WriterRepository writerRepository) {
        this.labelRepository = labelRepository;
        this.writerRepository = writerRepository;
    }

    @Override
    public Post getById(Long id) throws Exception {
        List<Post> posts = stringToData(IO_operations.read(FILE_NAME));

        Post current = null;
        for (Post c : posts) {
            if (c.getId().equals(id))
            {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception(ForConsole.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Post post) throws Exception {
        List<Post> posts = stringToData( IO_operations.read(FILE_NAME));
        Post removePost = null;
        for (Post c: posts) {
            if (c.getId().equals(post.getId()))
            {
                removePost = c;
                break;
            }
        }

        posts.remove(removePost);
        IO_operations.writeList(FILE_NAME, dataToString(posts));
    }

    @Override
    public void update(Post post) throws Exception {
        delete(getById(post.getId()));
        save(post);
    }

    @Override
    public void save(Post post)
    {
        IO_operations.write(FILE_NAME, dataToString(post));
    }

    @Override
    public void checkEdit(Long id) throws Exception
    {
        Post post = getById(id);

        if (post.getPostStatus() == PostStatus.DELETED) {
            String cannotEditDeletedPostMessage = "Can't edit deleted post!";
            throw new Exception(cannotEditDeletedPostMessage);
        }

        if (post.getPostStatus() == PostStatus.UNDER_REVIEW) {
            String cannotEditUNDER_REVIEWPostMessage = "Can't edit under reviewed post!";
            throw new Exception(cannotEditUNDER_REVIEWPostMessage);
        }
    }

    @Override
    public void checkLabel(Long id) throws Exception {
        labelRepository.getById(id);
    }

    @Override
    public void checkWriter(Long id) throws Exception {
        writerRepository.getById(id);
    }

    @Override
    public List<Post> getAll() throws Exception {
        return stringToData(IO_operations.read(FILE_NAME));
    }

    @Override
    public Long getLastId() throws Exception {
        List<Post> posts = stringToData(IO_operations.read(FILE_NAME));
        posts.sort(Comparator.comparing(Entity::getId));

        if (posts.size() != 0) {
            return posts.get(posts.size() - 1).getId();
        }
        return 0L; // long zero!
    }

    @Override
    public List<Post> stringToData(List<String> elements) throws Exception
    {
        List<Post> posts = new ArrayList<>();

        for (String str : elements)
        {
            String[] parts = str.split(",");
            Post post = new Post();

            post.setId(Long.parseLong(parts[0]));
            post.setContent(parts[1]);
            post.setPostStatus(PostStatus.valueOf(parts[2]));

            Long customerId = Long.parseLong(parts[3]);
            post.setWriterId(customerId);
            post.setWriter(writerRepository.getById(customerId));

            String[] cIds = parts[4].split("/");
            HashSet<Label> labels = new HashSet<>();
            HashSet<Long> labelIds = new HashSet<>();
            for (String id : cIds)
            {
                Long labelId = Long.parseLong(id);
                labelIds.add(labelId);
                labels.add(labelRepository.getById(labelId));
            }
            post.setLabelIds(labelIds);
            post.setLabels(labels);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public List<String> dataToString(List<Post> posts)
    {
        List<String> data = new ArrayList<>();
        for (Post p : posts)
        {
            data.add(dataToString(p));
        }
        return data;
    }

    @Override
    public String dataToString(Post p)
    {
        String data = p.getId() + "," + p.getContent() + "," + p.getPostStatus() + "," + p.getWriterId() + ",";
        StringJoiner joiner = new StringJoiner("/");
        for (Long c : p.getLabelIds())
        {
            joiner.add(c+"");
        }
        data += joiner;

        return data;
    }

    @Override
    public boolean isContainLabel(Label label) throws Exception
    {
        List<Post> posts = stringToData(IO_operations.read(FILE_NAME));
        for (Post p : posts)
        {
            if (p.getLabels().contains(label))
                return true;
        }
        return false;
    }

    @Override
    public boolean isContainWriter(Writer writer) throws Exception
    {
        List<Post> posts = stringToData(IO_operations.read(FILE_NAME));
        for (Post p : posts)
        {
            if (p.getWriter() == writer)
                return true;
        }
        return false;
    }
}