package com.marouane.data.filtringspecification.book.infrastructure.repository;

import com.marouane.data.filtringspecification.book.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookJpaRepository extends CrudRepository<BookEntity, UUID>, JpaSpecificationExecutor<BookEntity> {}
