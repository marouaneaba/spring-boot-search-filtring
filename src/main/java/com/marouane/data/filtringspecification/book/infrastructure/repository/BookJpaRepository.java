package com.marouane.data.filtringspecification.book.infrastructure.repository;

import com.marouane.data.filtringspecification.book.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, UUID>, JpaSpecificationExecutor<BookEntity> {}
