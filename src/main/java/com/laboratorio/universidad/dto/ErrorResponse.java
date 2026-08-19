package com.laboratorio.universidad.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String transaction;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String error, String message, String transaction) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.transaction = transaction;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getTransaction() { return transaction; }
}
