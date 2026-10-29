package org.example.test.repository;

import org.example.test.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    /**
     * Tìm kiếm linh hoạt theo:
     * - Tên khách hàng (tìm kiếm tương đối LIKE %name%)
     * - Loại dịch vụ ("Đất", "Nhà và đất" hoặc "Nhà đất")
     * - Kết hợp cả hai tiêu chí
     */
    @Query("SELECT g FROM GiaoDich g JOIN g.khachHang k " +
           "WHERE (:tenKhachHang IS NULL OR :tenKhachHang = '' OR LOWER(k.tenKhachHang) LIKE LOWER(CONCAT('%', :tenKhachHang, '%'))) " +
           "AND (:loaiDichVu IS NULL OR :loaiDichVu = '' OR g.loaiDichVu = :loaiDichVu) " +
           "ORDER BY g.maGiaoDich ASC")
    List<GiaoDich> searchTransactions(@Param("tenKhachHang") String tenKhachHang, 
                                      @Param("loaiDichVu") String loaiDichVu);

    boolean existsByMaGiaoDich(String maGiaoDich);
}
