package org.example.test.repository;

import org.example.test.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    List<KhachHang> findAllByOrderByTenKhachHangAsc();
}
