package com.example.sample.repository;

import com.example.sample.entity.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    Page<Sample> findByNameContaining(String name, Pageable pageable);

    Slice<Sample> findByEmailContaining(String email, Pageable pageable);
}
