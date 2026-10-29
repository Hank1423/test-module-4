USE real_estate_db;

-- Xóa bảng cũ nếu tồn tại (theo thứ tự khóa ngoại)
DROP TABLE IF EXISTS giao_dich;
DROP TABLE IF EXISTS khach_hang;

-- 1. Bảng khách hàng (khach_hang)
CREATE TABLE khach_hang (
    ma_khach_hang VARCHAR(20) PRIMARY KEY,
    ten_khach_hang VARCHAR(100) NOT NULL,
    so_dien_thoai VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Bảng giao dịch nhà đất (giao_dich)
CREATE TABLE giao_dich (
    ma_giao_dich VARCHAR(20) PRIMARY KEY,
    ma_khach_hang VARCHAR(20) NOT NULL,
    ngay_giao_dich DATE NOT NULL,
    loai_dich_vu VARCHAR(50) NOT NULL,
    don_gia DOUBLE NOT NULL,
    dien_tich DOUBLE NOT NULL,
    CONSTRAINT fk_giao_dich_khach_hang FOREIGN KEY (ma_khach_hang) 
        REFERENCES khach_hang(ma_khach_hang) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Thêm dữ liệu mẫu khách hàng (theo đúng đề bài)
INSERT INTO khach_hang (ma_khach_hang, ten_khach_hang, so_dien_thoai, email) VALUES
('KH-001', 'Nguyễn Văn A', '0905973155', 'nguyenvana@gmail.com'),
('KH-002', 'Nguyễn Văn B', '0912345678', 'nguyenvanb@gmail.com'),
('KH-003', 'Nguyễn Văn C', '0987654321', 'nguyenvanc@gmail.com'),
('KH-004', 'Trần Thị Mai', '0935123456', 'tranmaibds@gmail.com'),
('KH-005', 'Lê Hoàng Nam', '0978999888', 'lehoangnam@yahoo.com');

-- Thêm dữ liệu mẫu giao dịch (theo bảng minh họa của đề bài và dữ liệu phong phú)
INSERT INTO giao_dich (ma_giao_dich, ma_khach_hang, ngay_giao_dich, loai_dich_vu, don_gia, dien_tich) VALUES
('MGD-0001', 'KH-001', '2026-11-15', 'Nhà đất', 2000000, 100),
('MGD-0002', 'KH-002', '2026-11-20', 'Đất', 3000000, 200),
('MGD-0003', 'KH-003', '2026-12-05', 'Nhà đất', 5000000, 100),
('MGD-0004', 'KH-004', '2026-12-12', 'Đất', 15000000, 120),
('MGD-0005', 'KH-005', '2026-11-28', 'Nhà đất', 25000000, 85);
