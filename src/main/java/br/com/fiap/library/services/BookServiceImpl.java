package br.com.fiap.library.services;

import br.com.fiap.library.entities.Book;
import br.com.fiap.library.exceptions.ResourceNotFoundException;
import br.com.fiap.library.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
    }

    @Override
    public Page<Book> findAll(int page, int size) {
        return this.repository
                .findAll(Pageable
                                .ofSize(size)
                                .withPage(page));
    }

    @Override
    public Book save(Book book) {
        return this.repository.save(book);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
