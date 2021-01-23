package com.VB2020.service.impl;


import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;
import com.VB2020.repository.PostRepository;
import com.VB2020.service.PostService;

import java.util.List;
import java.util.Set;

public class JavaIOPostServiceImpl implements PostService {

    private PostRepository postRepo;

    public JavaIOPostServiceImpl(PostRepository projectRepo) {
        this.postRepo = projectRepo;
    }

    @Override
    public Post getById(Long id) throws Exception {
        return postRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Post post = getById(id);

        if (post.getPostStatus() == PostStatus.UNDER_REVIEW) {
            String cannotDeleteUNDER_REVIEWPostMessage = "Can't delete UNDER_REVIEWed post!";
            throw new Exception(cannotDeleteUNDER_REVIEWPostMessage);
        }
        post.setPostStatus(PostStatus.DELETED);

        postRepo.update(post);
    }

    @Override
    public void create(String content, PostStatus postStatus, Long writerId, Set<Long> labelIds) throws Exception {
        Post post = new Post();
        post.setId(postRepo.getLastId() + 1);
        post.setContent(content);
        post.setPostStatus(postStatus);
        post.setLabelIds(labelIds);
        post.setWriterId(writerId);
        postRepo.save(post);
    }

    @Override
    public void update(Long id, String content, Long writerId, Set<Long> labelIds) throws Exception {
        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.setPostStatus(PostStatus.ACTIVE);
        post.setLabelIds(labelIds);
        post.setWriterId(writerId);
        postRepo.update(post);
    }

    @Override
    public void under_review(Long id) throws Exception {
        Post post = getById(id);
        if (post.getPostStatus() == PostStatus.DELETED) {
            String cannotUNDER_REVIEWDeletedPostMessage = "Can't UNDER_REVIEW deleted post!";
            throw new Exception(cannotUNDER_REVIEWDeletedPostMessage);
        }
        post.setPostStatus(PostStatus.UNDER_REVIEW);

        postRepo.update(post);
    }

    @Override
    public void checkEdit(Long id) throws Exception {
        postRepo.checkEdit(id);
    }

    @Override
    public void checkLabel(Long id) throws Exception {
        postRepo.checkLabel(id);
    }

    @Override
    public void checkWriter(Long id) throws Exception {
        postRepo.checkWriter(id);
    }

    @Override
    public List<Post> getAll() throws Exception {

        return postRepo.getAll();
    }
}
