package com.marouane.data.filtringspecification.book.infrastructure.book;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import com.marouane.data.filtringspecification.book.domain.Book;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {

    private final BookFilter bookFilter;

    public BookSpecification(BookFilter bookFilter) {
        this.bookFilter = bookFilter;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if(!Strings.isBlank(bookFilter.name())){
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), bookFilter.name());
            predicates.add(namePredicate);
        }

        if(!Strings.isBlank(bookFilter.isbn())){
            Predicate isbnPredicate = criteriaBuilder.like(root.get("isbn"), bookFilter.isbn());
            predicates.add(isbnPredicate);
        }

        if(null != bookFilter.bookType()){
            Predicate bookTypePredicate = criteriaBuilder.like(root.get("bookType"), bookFilter.bookType().name());
            predicates.add(bookTypePredicate);
        }

        if(!Strings.isBlank(bookFilter.country())){
            Predicate countryPredicate = criteriaBuilder.like(root.get("country"), bookFilter.country());
            predicates.add(countryPredicate);
        }

        if(!Strings.isBlank(bookFilter.createDate())){
            Predicate createDatePredicate = criteriaBuilder.like(root.get("createDate"), bookFilter.createDate());
            predicates.add(createDatePredicate);
        }

        if(null != bookFilter.stock()){
            Predicate stockPredicate = criteriaBuilder.equal(root.get("stock"), bookFilter.stock());
            predicates.add(stockPredicate);
        }

        if (!CollectionUtils.isEmpty(bookFilter.sort())) {
            List<Order> orders = bookFilter.sort().stream().map(filedSort -> criteriaBuilder.desc(root.get(filedSort))).toList();
            query.orderBy(orders);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
