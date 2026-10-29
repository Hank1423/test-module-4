package org.example.test.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "khach_hang")
@Data
public class KhachHang {

    @Id
    @Column(name = "ma_khach_hang", length = 20)
    private String maKhachHang;

    @Column(name = "ten_khach_hang", nullable = false, length = 100)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GiaoDich> danhSachGiaoDich;

    public KhachHang() {}

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }
}
