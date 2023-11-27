package com.example.sample.entity;

import com.example.sample.dto.SampleDto;
import com.example.sample.dto.RequestUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private Integer age;


    @Builder
    public Sample(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public SampleDto toDto() {
        return SampleDto.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .age(this.age)
                .build();
    }

    public void updateName(RequestUpdateDto updateDto) {
        if (updateDto.getName() != null) {
            this.name = updateDto.getName();
        }
    }
}
