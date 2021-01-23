package com.VB2020.service;


import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;

import java.util.Set;

public interface PostService extends GenericService <Post, Long> {

    void create(String content, PostStatus postStatus, Long writerId, Set<Long> labelIds) throws Exception;

    void update(Long id, String content, Long writerId, Set<Long> labelIds) throws Exception;

    void under_review(Long id) throws Exception;

    void checkEdit(Long id) throws Exception;

    void checkLabel(Long id) throws Exception;

    void checkWriter(Long id) throws Exception;
}
