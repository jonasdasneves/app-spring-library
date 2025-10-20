package br.com.fiap.library.services;

import br.com.fiap.library.entities.Author;
import br.com.fiap.library.entities.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    public Author findById(Long id);
    public Page<Author> findAll(int page, int size);
    public Page<Book> findAllBooksByAuthorId(Long id, int page, int size);
    public Author save(Author author);
    public Author update(Author author);
    public void delete(Long id);
}
