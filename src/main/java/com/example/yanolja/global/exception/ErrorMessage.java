package com.example.yanolja.global.exception;

import java.time.LocalDateTime;

public record ErrorMessage(String message, LocalDateTime timestamp) {

}
