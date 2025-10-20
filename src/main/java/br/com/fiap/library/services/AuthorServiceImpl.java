package br.com.fiap.library.services;

import br.com.fiap.library.entities.Author;
import br.com.fiap.library.entities.Book;
import br.com.fiap.library.exceptions.ResourceNotFoundException;
import br.com.fiap.library.repositories.AuthorRepository;
import br.com.fiap.library.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
    }

    @Override
    public Page<Author> findAll(int page, int size) {
        return this.repository
                .findAll(Pageable
                        .ofSize(size)
                        .withPage(page));
    }

    @Override
    public Page<Book> findAllBooksByAuthorId(Long id, int page, int size) {
        return this.bookRepository
                .findAllByAuthor_Id(Math.toIntExact(id), Pageable
                        .ofSize(size)
                        .withPage(page));
    }

    @Override
    public Author save(Author author) {
        return this.repository.save(author);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
