package br.com.fiap.library.mappers;

import br.com.fiap.library.dtos.AuthorRecord;
import br.com.fiap.library.entities.Author;

public class AuthorMapper {
    public static Author fromRecord(AuthorRecord record) {
        return new Author(
                record.id(),
                record.name(),
                record.email()
        );
    }

    public static AuthorRecord toRecord(Author author) {
        return new AuthorRecord(
                author.getId(),
                author.getName(),
                author.getEmail());
    }

    private AuthorMapper() {}
}
