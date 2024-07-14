package com.example.demo.response;

import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private int status;
    private Boolean success;
    private T data;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
