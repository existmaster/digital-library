package com.pear.digitallibrary.controller;

import com.pear.digitallibrary.domain.Book;
import com.pear.digitallibrary.service.BookRegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRegistrationService bookRegistrationService;

    @Test
    public void registerBookWithValidISBN() throws Exception {
        mockMvc.perform(post("/books/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"9780553213119\",\"title\":\"Book Title\",\"author\":\"Author Name\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void registerBookWithInvalidISBN() throws Exception {
        doThrow(new NumberFormatException("Invalid ISBN"))
                .when(bookRegistrationService).registerBook(any(Book.class));

        mockMvc.perform(post("/books/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"invalidISBN\",\"title\":\"Book Title\",\"author\":\"Author Name\"}"))
                .andExpect(status().isBadRequest());
    }
}
