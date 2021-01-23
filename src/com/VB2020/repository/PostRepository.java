package com.VB2020.repository;


import com.VB2020.model.Label;
import com.VB2020.model.Post;
import com.VB2020.model.Writer;

public interface PostRepository extends GenericRepository<Post, Long> {

    boolean isContainLabel(Label label) throws Exception;
    boolean isContainWriter(Writer writer) throws Exception;
    void checkEdit(Long id) throws Exception;
    void checkLabel(Long id) throws Exception;
    void checkWriter(Long id) throws Exception;
}
