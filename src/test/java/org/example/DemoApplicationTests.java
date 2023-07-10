package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    private static final int DEV_PORT = 8080;
    private static final int PROD_PORT = 8081;
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> myAppDev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myAppDev.start();
        myAppProd.start();
    }

    @ParameterizedTest
    @ValueSource(strings = { "http://localhost:" + DEV_PORT + "/profile", "http:localhost:" + PROD_PORT + "/profile" })
    void contextLoads(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expected = url.contains("dev") ? "Current profile is dev" : "Current profile is production";
        Assertions.assertEquals(expected, response.getBody());
    }
}
