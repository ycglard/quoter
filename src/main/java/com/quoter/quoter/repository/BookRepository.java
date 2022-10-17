package com.quoter.quoter.repository;
import com.quoter.quoter.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PostRemove;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, CustomBookRepository {

}
