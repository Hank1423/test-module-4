package org.example.test.service.impl;

import org.example.test.model.KhachHang;
import org.example.test.repository.KhachHangRepository;
import org.example.test.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KhachHangService implements IKhachHangService {

    private final KhachHangRepository khachHangRepository;

    @Autowired
    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KhachHang> findAll() {
        return khachHangRepository.findAllByOrderByTenKhachHangAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KhachHang> findById(String id) {
        return khachHangRepository.findById(id);
    }

    @Override
    public void save(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }
}
