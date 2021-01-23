package com.VB2020.controller;


import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;
import com.VB2020.service.PostService;

import java.util.List;
import java.util.Set;

public class PostController {

    PostService postService;

    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    public List<Post> getAll() throws Exception {
        return postService.getAll();
    }

    public Post getById(Long id) throws Exception {
        return postService.getById(id);
    }

    public void create(String name, PostStatus postStatus, Long writerId, Set<Long> labelIds) throws Exception {
        postService.create(name, postStatus, writerId, labelIds);
    }

    public void update(Long id, String name, Long writerId, Set<Long> labelIds) throws Exception {
        postService.update(id, name, writerId, labelIds);
    }

    public void under_review(Long id) throws Exception {
        postService.under_review(id);
    }

    public void checkEdit(Long id) throws Exception {
        postService.checkEdit(id);
    }

    public void checkLabel(Long id) throws Exception {
        postService.checkLabel(id);
    }

    public void checkWriter(Long id) throws Exception {
        postService.checkWriter(id);
    }

    public void delete(Long id) throws Exception {
        postService.delete(id);
    }
}
