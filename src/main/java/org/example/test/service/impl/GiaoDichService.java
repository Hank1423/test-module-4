package org.example.test.service.impl;

import org.example.test.dto.GiaoDichDto;
import org.example.test.model.GiaoDich;
import org.example.test.model.KhachHang;
import org.example.test.repository.GiaoDichRepository;
import org.example.test.repository.KhachHangRepository;
import org.example.test.service.IGiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GiaoDichService implements IGiaoDichService {

    private final GiaoDichRepository giaoDichRepository;
    private final KhachHangRepository khachHangRepository;

    @Autowired
    public GiaoDichService(GiaoDichRepository giaoDichRepository, KhachHangRepository khachHangRepository) {
        this.giaoDichRepository = giaoDichRepository;
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiaoDich> findAll() {
        return giaoDichRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GiaoDich> findById(String maGiaoDich) {
        return giaoDichRepository.findById(maGiaoDich);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiaoDich> search(String tenKhachHang, String loaiDichVu) {
        String cleanTen = (tenKhachHang != null) ? tenKhachHang.trim() : "";
        String cleanLoai = (loaiDichVu != null) ? loaiDichVu.trim() : "";
        return giaoDichRepository.searchTransactions(cleanTen, cleanLoai);
    }

    @Override
    public void save(GiaoDichDto dto) {
        KhachHang khachHang = khachHangRepository.findById(dto.getMaKhachHang())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng với mã: " + dto.getMaKhachHang()));

        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(dto.getMaGiaoDich().trim());
        giaoDich.setKhachHang(khachHang);
        giaoDich.setNgayGiaoDich(dto.getNgayGiaoDich());
        giaoDich.setLoaiDichVu(dto.getLoaiDichVu());
        giaoDich.setDonGia(dto.getDonGia());
        giaoDich.setDienTich(dto.getDienTich());

        giaoDichRepository.save(giaoDich);
    }

    @Override
    public boolean delete(String maGiaoDich) {
        if (giaoDichRepository.existsById(maGiaoDich)) {
            giaoDichRepository.deleteById(maGiaoDich);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String maGiaoDich) {
        return giaoDichRepository.existsById(maGiaoDich);
    }

    @Override
    public void validateBusinessRules(GiaoDichDto dto, BindingResult bindingResult) {
        if (dto.getMaGiaoDich() != null && !dto.getMaGiaoDich().isBlank()
                && giaoDichRepository.existsById(dto.getMaGiaoDich().trim())) {
            bindingResult.addError(new FieldError("giaoDichDto", "maGiaoDich",
                    "Mã giao dịch [" + dto.getMaGiaoDich() + "] đã tồn tại trong hệ thống!"));
        }

        if (dto.getNgayGiaoDich() != null && !dto.getNgayGiaoDich().isAfter(LocalDate.now())) {
            String todayFormatted = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            bindingResult.addError(new FieldError("giaoDichDto", "ngayGiaoDich",
                    "Ngày giao dịch phải sau ngày " + todayFormatted + "!"));
        }

        if (dto.getMaKhachHang() != null && !dto.getMaKhachHang().isBlank()
                && !khachHangRepository.existsById(dto.getMaKhachHang())) {
            bindingResult.addError(new FieldError("giaoDichDto", "maKhachHang",
                    "Khách hàng đã chọn không tồn tại trong hệ thống!"));
        }
    }
}
