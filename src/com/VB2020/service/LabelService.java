package com.VB2020.service;

import com.VB2020.model.Label;

public interface LabelService extends GenericService <Label, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
