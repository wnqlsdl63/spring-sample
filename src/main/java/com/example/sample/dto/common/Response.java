package com.example.sample.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class Response<T> {
    private String status = HttpStatus.OK.name();
    private Integer code = HttpStatus.OK.value();
    private T result;

    @Builder
    public Response(String status, Integer code, T result) {
        this.status = status;
        this.code = code;
        this.result = result;
    }

    public static <T> Response<T> of(T data) {
        Response<T> response = new Response<>();
        response.setResult(data);
        return response;
    }
}
