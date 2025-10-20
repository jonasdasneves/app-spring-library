package br.com.fiap.library.integration;

import br.com.fiap.library.entities.Author;
import br.com.fiap.library.repositories.AuthorRepository;
import br.com.fiap.library.repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Transactional
public class AuthorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        // Clean up database before each test
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void deveCriarAuthorViaController() throws Exception {
        this.mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "J. R. R. Tolkien",
                                    "email": "integration@example.com"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is("J. R. R. Tolkien")))
                .andExpect(jsonPath("$.email", is("integration@example.com")));

        // Verify data was actually persisted in database
        var authors = authorRepository.findAll();
        assert authors.size() == 1;
        assert authors.get(0).getName().equals("J. R. R. Tolkien");
    }
}
