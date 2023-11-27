package com.example.sample.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class SliceDto<T> {
    private List<T> data;
    private Pagination pagination;

    public SliceDto(final Slice<T> source) {
        data = source.getContent();
        pagination = Pagination.builder()
                .isFirst(source.isFirst())
                .isLast(source.isLast())
                .hasPrevious(source.hasPrevious())
                .hasNext(source.hasNext())
                .build();
    }
}
