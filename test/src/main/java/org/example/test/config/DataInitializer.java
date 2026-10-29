package org.example.test.config;

import org.example.test.model.GiaoDich;
import org.example.test.model.KhachHang;
import org.example.test.repository.GiaoDichRepository;
import org.example.test.repository.KhachHangRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(KhachHangRepository khachHangRepo, GiaoDichRepository giaoDichRepo) {
        return args -> {
            if (khachHangRepo.count() == 0) {
                KhachHang kh1 = new KhachHang("KH-001", "Nguyễn Văn A", "0905973155", "nguyenvana@gmail.com");
                KhachHang kh2 = new KhachHang("KH-002", "Nguyễn Văn B", "0912345678", "nguyenvanb@gmail.com");
                KhachHang kh3 = new KhachHang("KH-003", "Nguyễn Văn C", "0987654321", "nguyenvanc@gmail.com");
                KhachHang kh4 = new KhachHang("KH-004", "Trần Thị Mai", "0935123456", "tranmaibds@gmail.com");
                KhachHang kh5 = new KhachHang("KH-005", "Lê Hoàng Nam", "0978999888", "lehoangnam@yahoo.com");
                khachHangRepo.saveAll(Arrays.asList(kh1, kh2, kh3, kh4, kh5));
            }

            if (giaoDichRepo.count() == 0) {
                KhachHang kh1 = khachHangRepo.findById("KH-001").orElseThrow();
                KhachHang kh2 = khachHangRepo.findById("KH-002").orElseThrow();
                KhachHang kh3 = khachHangRepo.findById("KH-003").orElseThrow();
                KhachHang kh4 = khachHangRepo.findById("KH-004").orElseThrow();
                KhachHang kh5 = khachHangRepo.findById("KH-005").orElseThrow();

                GiaoDich gd1 = new GiaoDich("MGD-0001", kh1, LocalDate.of(2026, 11, 15), "Nhà đất", 2000000.0, 100.0);
                GiaoDich gd2 = new GiaoDich("MGD-0002", kh2, LocalDate.of(2026, 11, 20), "Đất", 3000000.0, 200.0);
                GiaoDich gd3 = new GiaoDich("MGD-0003", kh3, LocalDate.of(2026, 12, 5), "Nhà đất", 5000000.0, 100.0);
                GiaoDich gd4 = new GiaoDich("MGD-0004", kh4, LocalDate.of(2026, 12, 12), "Đất", 15000000.0, 120.0);
                GiaoDich gd5 = new GiaoDich("MGD-0005", kh5, LocalDate.of(2026, 11, 28), "Nhà đất", 25000000.0, 85.0);

                giaoDichRepo.saveAll(Arrays.asList(gd1, gd2, gd3, gd4, gd5));
            }
        };
    }
}
