package com.practice.rabbitmq;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import net.minidev.json.JSONUtil;
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RabbitmqApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)

@AutoConfigureTestDatabase
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")
public class AuthorRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void tearUp() {
        createTestAuthor();
    }

    @After
    public void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    public void shouldReturnHttpStatus200WhenHavingCorrectRequest() throws Exception {
        mvc.perform(get("/author/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$[0].userName", is("author1"))));

    }

    @Test
    public void shouldPersistDataWhenPostingValidAuthor() throws Exception {
        mvc.perform(post("/author/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"userName\": \"author_test\", \"password\": \"author\", \"active\": true }"));

        List<Author> authors = authorRepository.findAll();
        assertEquals(authors.size(), 2);
        assertThat(authors).extracting(Author::getUserName).containsExactly("author1", "author_test");
    }

    private void createTestAuthor() {
        Author author = new Author("author1", "author1", true);
        authorRepository.saveAndFlush(author);
    }
}
