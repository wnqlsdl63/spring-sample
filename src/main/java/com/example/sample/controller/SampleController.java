package com.example.sample.controller;

import com.example.sample.dto.common.SliceDto;
import com.example.sample.dto.common.PageDto;
import com.example.sample.dto.RequestSaveDto;
import com.example.sample.dto.RequestUpdateDto;
import com.example.sample.dto.common.Response;
import com.example.sample.dto.SampleDto;
import com.example.sample.service.SampleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {
    private final SampleService sampleService;

    @Value("${isActive}")
    private boolean isActive;

    @GetMapping("/{id}")
    public ResponseEntity<Response<SampleDto>> findById(@PathVariable Long id) {
        Response<SampleDto> response = Response.of(sampleService.findById(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<Response<PageDto<SampleDto>>> findSamplePages(@RequestParam(required = false) String name, Pageable pageable) {
        Response<PageDto<SampleDto>> response = Response.of(sampleService.findSamplePages(name, pageable));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/slice")
    public ResponseEntity<Response<SliceDto<SampleDto>>> findSampleSlices(@RequestParam(required = false) String email, @PageableDefault(size = 3) Pageable pageable) {
        Response<SliceDto<SampleDto>> response = Response.of(sampleService.findSampleSlices(email, pageable));

        log.info("response => {}", response);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<SampleDto>> save(@RequestBody @Valid RequestSaveDto requestSaveDto) {
        Response<SampleDto> response = Response.of(sampleService.save(requestSaveDto));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<SampleDto>> update(@PathVariable Long id, @RequestBody @Valid RequestUpdateDto requestUpdateDto) {
        Response<SampleDto> response = Response.of(sampleService.update(id, requestUpdateDto));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable Long id) {
        Response<Boolean> response = Response.of(sampleService.delete(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/old")
    public ResponseEntity<Response<String>> old(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isActive) {
            request.getRequestDispatcher("/sample/new").forward(request, response);
        }

        Response<String> res = Response.of("old response success");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/new")
    public ResponseEntity<Response<String>> handleNew(@RequestParam String name) {
        Response<String> response = Response.of(name + " foward success");

        return ResponseEntity.ok(response);
    }
}
