package br.com.fiap.library.repositories;

import br.com.fiap.library.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByAuthor_Id(int authorId, Pageable pageable);
}
