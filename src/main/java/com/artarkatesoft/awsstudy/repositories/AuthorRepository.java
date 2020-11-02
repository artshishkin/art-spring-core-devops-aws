package com.artarkatesoft.awsstudy.repositories;

import com.artarkatesoft.awsstudy.domain.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 5/6/16.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
