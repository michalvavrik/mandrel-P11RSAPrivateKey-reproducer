package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ReproResourceTest {
    @Test
    void testDecoding() {
        given()
          .when().get("/repro")
          .then()
             .statusCode(204);
    }

}