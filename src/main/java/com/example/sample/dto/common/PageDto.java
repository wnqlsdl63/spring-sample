package com.example.sample.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class PageDto<T> {
    private List<T> data;
    private Pagination pagination;

    public PageDto(final Page<T> source) {
        data = source.getContent();
        pagination = Pagination.builder()
                .totalPages(source.getTotalPages())
                .totalElements(source.getTotalElements())
                .currentPage(source.getNumber())
                .build();
    }


}
