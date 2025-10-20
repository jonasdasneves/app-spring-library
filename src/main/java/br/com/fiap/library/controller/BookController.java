package br.com.fiap.library.controller;

import br.com.fiap.library.dtos.BookRecord;
import br.com.fiap.library.entities.Book;
import br.com.fiap.library.mappers.BookMapper;
import br.com.fiap.library.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRecord> findById(@PathVariable Long id) {
        BookRecord book = BookMapper.toRecord(this.service.findById(id));
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Page<BookRecord>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<BookRecord> bookPage = this.service.findAll(page, size).map(BookMapper::toRecord);

        if (bookPage.hasContent()) {
            return ResponseEntity.ok(bookPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookRecord> save(@RequestBody BookRecord book) {
        Book newBook = this.service.save(BookMapper.fromRecord(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.toRecord(newBook));
    }

    @PutMapping
    public ResponseEntity<BookRecord> update(@RequestBody BookRecord book) {
        Book updBook = this.service.save(BookMapper.fromRecord(book));
        return ResponseEntity.ok(BookMapper.toRecord(updBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
