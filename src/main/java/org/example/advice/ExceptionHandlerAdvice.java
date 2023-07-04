package org.example.advice;

import org.example.exception.InvalidCredentials;
import org.example.exception.UnauthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
        private static final String INVALID_CREDENTIALS_MSG = "User name or password is empty";
        private static final String UNAUTHORIZED_USER_MSG = "Invalid username or password";

        @ExceptionHandler(InvalidCredentials.class)
        public ResponseEntity<String> iaeHandler(InvalidCredentials e) {
            return new ResponseEntity<>(INVALID_CREDENTIALS_MSG, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UnauthorizedUser.class)
        public ResponseEntity<String> iaeHandler(UnauthorizedUser e) {
            return new ResponseEntity<>(UNAUTHORIZED_USER_MSG, HttpStatus.UNAUTHORIZED);
        }
}
