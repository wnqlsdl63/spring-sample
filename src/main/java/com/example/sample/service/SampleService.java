package com.example.sample.service;

import com.example.sample.dto.common.SliceDto;
import com.example.sample.dto.common.PageDto;
import com.example.sample.dto.SampleDto;
import com.example.sample.dto.RequestSaveDto;
import com.example.sample.dto.RequestUpdateDto;
import com.example.sample.entity.Sample;
import com.example.sample.exception.NotFoundException;
import com.example.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SampleService {
    private final SampleRepository sampleRepository;

    public SampleDto save(RequestSaveDto saveDto) {
        Sample savedSample = sampleRepository.save(saveDto.toEntity());

        return savedSample.toDto();
    }

    @Transactional(readOnly = true)
    public SampleDto findById(Long id) {
        return sampleRepository.findById(id)
                .map(Sample::toDto)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public PageDto<SampleDto> findSamplePages(String name, Pageable pageable) {
        Page<SampleDto> result;

        if (name == null || name.isEmpty()) {
            result = sampleRepository.findAll(pageable).map(Sample::toDto);
        } else {
            result = sampleRepository.findByNameContaining(name, pageable).map(Sample::toDto);
        }

        return new PageDto<>(result);
    }

    @Transactional(readOnly = true)
    public SliceDto<SampleDto> findSampleSlices(String email, Pageable pageable) {
        Slice<SampleDto> result;

        if (email == null || email.isEmpty()) {
            result = sampleRepository.findAll(pageable).map(Sample::toDto);
        } else {
            result = sampleRepository.findByEmailContaining(email, pageable).map(Sample::toDto);
        }

        return new SliceDto<>(result);
    }

    public boolean delete(Long id) {
        sampleRepository.deleteById(id);

        return true;
    }

    @Transactional
    public SampleDto update(Long id, RequestUpdateDto updateDto) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다."));

        sample.updateName(updateDto);

        return sampleRepository.save(sample).toDto();
    }

}
