package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenericService<T> {
    Iterable<T> findAll();
    void save(T t);
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(Long id);
    void deleteById(Long id);
}
