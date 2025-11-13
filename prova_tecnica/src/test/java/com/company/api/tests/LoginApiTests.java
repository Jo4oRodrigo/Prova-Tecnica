package com.company.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginApiTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
    }

    @Test
    void shouldReturn200WithTokenAndProfile() {
        given()
            .contentType(ContentType.JSON)
            .body("{"username":"user@example.com","password":"validPassword"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(200)
            .body("token", not(emptyString()))
            .body("profile", equalTo("USER"));
    }

    @Test
    void shouldReturn401ForInvalidCredentials() {
        given()
            .contentType(ContentType.JSON)
            .body("{"username":"user@example.com","password":"wrong"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(401)
            .body("message", containsString("Credenciais inválidas"));
    }

    @Test
    void shouldReturn403ForAccessDenied() {
        given()
            .contentType(ContentType.JSON)
            .body("{"username":"visitor@example.com","password":"visitorPass"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(403)
            .body("message", containsString("Acesso negado"));
    }

    @Test
    void shouldReturn423ForBlockedUser() {
        given()
            .contentType(ContentType.JSON)
            .body("{"username":"blocked@example.com","password":"any"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(423)
            .body("message", containsString("bloqueado"));
    }
}
