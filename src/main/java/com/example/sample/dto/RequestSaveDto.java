package com.example.sample.dto;

import com.example.sample.entity.Sample;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestSaveDto {
    @Email
    private String email;

    @NotEmpty(message = "이름은 필수값 입니다.")
    private String name;

    @Min(value = 30, message = "나이는 30보다 작을수 없습니다.")
    private Integer age;

    public Sample toEntity() {
        return Sample.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .build();
    }
}
