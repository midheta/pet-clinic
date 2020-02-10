package com.petclinicdemo.service;

import java.util.List;

public interface CrudService<T> {

    public List<T> findAll();

    public T findOneById(Long id);

    public T save(T object);

    public void delete(T object);

    public void deleteById(Long id);
}
