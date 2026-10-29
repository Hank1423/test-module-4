package org.example.test.service;

import org.example.test.model.KhachHang;

import java.util.List;
import java.util.Optional;

public interface IKhachHangService {
    List<KhachHang> findAll();
    Optional<KhachHang> findById(String id);
    void save(KhachHang khachHang);
}
