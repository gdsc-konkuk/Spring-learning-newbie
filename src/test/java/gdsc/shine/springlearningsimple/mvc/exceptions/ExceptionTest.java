package gdsc.shine.springlearningsimple.mvc.exceptions;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * ExceptionsController > handle 메서드
     */
    @DisplayName("Exceptions - hello")
    @Test
    void exceptionHandler() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/exceptions/hello")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("HelloException"));
    }

    /**
     * ExceptionAdvice > handle 메서드
     */
    @DisplayName("Exceptions - hi")
    @Test
    void controllerAdvice() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/exceptions/hi")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("HiException"));
    }
}
