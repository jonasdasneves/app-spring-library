package br.com.fiap.library.services;

import br.com.fiap.library.entities.Author;
import br.com.fiap.library.entities.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    public Book findById(int id);
    public Page<Book> findAll(int page, int size);
    public Book save(Book book);
    public Book update(Book book);
    public void delete(Long id);
}
