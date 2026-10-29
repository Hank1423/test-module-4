package org.example.test.service;

import org.example.test.dto.GiaoDichDto;
import org.example.test.model.GiaoDich;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface IGiaoDichService {
    List<GiaoDich> findAll();
    Optional<GiaoDich> findById(String maGiaoDich);
    List<GiaoDich> search(String tenKhachHang, String loaiDichVu);
    void save(GiaoDichDto giaoDichDto);
    boolean delete(String maGiaoDich);
    boolean existsById(String maGiaoDich);
    void validateBusinessRules(GiaoDichDto dto, BindingResult bindingResult);
}
