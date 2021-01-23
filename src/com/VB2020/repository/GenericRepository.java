package com.VB2020.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id) throws Exception;

    void delete(T element) throws Exception;

    void update(T element) throws Exception;

    void save(T element);

    List<T> getAll() throws Exception;

    ID getLastId() throws Exception;

    List<T> stringToData(List<String> elements) throws Exception;

    List<String> dataToString(List<T> elements);

    String dataToString(T element);
}