package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        // 완전한 스프링 웹 어플리케이션 콘텍스트와 임의의 포트를 듣는
        // 서블릿 컨테이너를 로드 한다.
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    void whenPostRequestThenBookCreated() {
        var expctedBook = new Book("1234", "title", "author", 9.90);
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expctedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(
                        v -> {
                            Assertions.assertThat(v).isNotNull();
                            Assertions.assertThat(v.isbn())
                                    .isEqualTo(expctedBook.isbn());
                        }
                );
    }
}
