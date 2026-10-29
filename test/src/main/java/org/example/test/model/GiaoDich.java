package org.example.test.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "giao_dich")
@Data
public class GiaoDich {

    @Id
    @Column(name = "ma_giao_dich", length = 20)
    private String maGiaoDich;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHang khachHang;

    @Column(name = "ngay_giao_dich", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayGiaoDich;

    @Column(name = "loai_dich_vu", nullable = false, length = 50)
    private String loaiDichVu;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "dien_tich", nullable = false)
    private Double dienTich;

    public GiaoDich() {}

    public GiaoDich(String maGiaoDich, KhachHang khachHang, LocalDate ngayGiaoDich, String loaiDichVu, Double donGia, Double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.khachHang = khachHang;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiDichVu = loaiDichVu;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public Double getTongTien() {
        return (donGia != null && dienTich != null) ? donGia * dienTich : 0.0;
    }
}
