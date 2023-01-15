package org.example.Repository;

import java.util.List;

public interface CRUDRepository<T> {

    T create(T t);

    List<T> findAll();
}
