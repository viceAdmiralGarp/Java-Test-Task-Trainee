package com.mmdev.traineejavadevelopertesttask.repository;

import com.mmdev.traineejavadevelopertesttask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
