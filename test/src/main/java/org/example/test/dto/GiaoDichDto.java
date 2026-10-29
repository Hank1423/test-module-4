package org.example.test.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class GiaoDichDto {

    @NotBlank(message = "Mã giao dịch không được để trống!")
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải có định dạng MGD-XXXX (XXXX là 4 chữ số, ví dụ: MGD-0001)!")
    private String maGiaoDich;

    @NotBlank(message = "Vui lòng chọn khách hàng!")
    private String maKhachHang;

    @NotNull(message = "Ngày giao dịch không được để trống!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayGiaoDich;

    @NotBlank(message = "Vui lòng chọn loại dịch vụ/bất động sản!")
    private String loaiDichVu;

    @NotNull(message = "Đơn giá không được để trống!")
    @DecimalMin(value = "500000.01", inclusive = true, message = "Đơn giá phải lớn hơn 500.000 VND!")
    private Double donGia;

    @NotNull(message = "Diện tích không được để trống!")
    @DecimalMin(value = "20.01", inclusive = true, message = "Diện tích phải lớn hơn 20 m²!")
    private Double dienTich;

    public GiaoDichDto() {}

    public GiaoDichDto(String maGiaoDich, String maKhachHang, LocalDate ngayGiaoDich, String loaiDichVu, Double donGia, Double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.maKhachHang = maKhachHang;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiDichVu = loaiDichVu;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }
}
