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

    // 유효한 ISBN을 사용하여 책을 등록하는 경우의 테스트 메서드입니다.
    // JSON 형식의 책 정보를 POST 요청으로 보내고, HTTP 상태 코드 200(OK)을 기대합니다.
    @Test
    public void registerBookWithValidISBN() throws Exception {
        mockMvc.perform(post("/books/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"9780553213119\",\"title\":\"Book Title\",\"author\":\"Author Name\"}"))
                .andExpect(status().isOk());
    }

    // 유효하지 않은 ISBN을 사용하여 책을 등록하는 경우의 테스트 메서드입니다.
    // BookRegistrationService에서 NumberFormatException을 발생시키도록 하고,
    // 이를 통해 등록 시도 시 HTTP 상태 코드 400(Bad Request)을 기대합니다.
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
