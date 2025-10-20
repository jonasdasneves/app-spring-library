package br.com.fiap.library.mappers;

import br.com.fiap.library.dtos.BookRecord;
import br.com.fiap.library.entities.Author;
import br.com.fiap.library.entities.Book;

public class BookMapper {

    public static Book fromRecord(BookRecord record) {

        return new Book(
            record.id(),
            record.title(),
            record.isbn(),
            record.authorId());
    }

    public static BookRecord toRecord(Book book) {
        return new BookRecord(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getId()
        );
    }
}
