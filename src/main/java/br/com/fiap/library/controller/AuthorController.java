package br.com.fiap.library.controller;

import br.com.fiap.library.dtos.AuthorRecord;
import br.com.fiap.library.dtos.BookRecord;
import br.com.fiap.library.entities.Author;
import br.com.fiap.library.entities.Book;
import br.com.fiap.library.mappers.AuthorMapper;
import br.com.fiap.library.mappers.BookMapper;
import br.com.fiap.library.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorRecord> findById(@PathVariable Long id) {
        AuthorRecord author = AuthorMapper.toRecord(this.service.findById(id));
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorRecord>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<AuthorRecord> authorPage = this.service.findAll(page, size).map(AuthorMapper::toRecord);

        if (authorPage.hasContent()) {
            return ResponseEntity.ok(authorPage);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<Page<BookRecord>> findAllBooksByAuthorId(@PathVariable Long authorId,
                                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                                   @RequestParam(required = false, defaultValue = "10") int size) {

        Page<BookRecord> bookPage = this.service.findAllBooksByAuthorId(authorId, page, size).map(BookMapper::toRecord);

        if (bookPage.hasContent()) {
            return ResponseEntity.ok(bookPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AuthorRecord> save(@RequestBody AuthorRecord author) {
        Author newAuthor = this.service.save(AuthorMapper.fromRecord(author));
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthorMapper.toRecord(newAuthor));
    }

    @PutMapping
    public ResponseEntity<AuthorRecord> update(@RequestBody AuthorRecord author) {
        Author updAuthor = this.service.save(AuthorMapper.fromRecord(author));
        return ResponseEntity.ok(AuthorMapper.toRecord(updAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
