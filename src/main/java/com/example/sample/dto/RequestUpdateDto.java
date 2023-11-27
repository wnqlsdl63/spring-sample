package com.example.sample.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUpdateDto {

    @NotEmpty(message = "이름은 필수값 입니다.")
    private String name;
}
