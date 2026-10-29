package org.example.test.controller;

import jakarta.validation.Valid;
import org.example.test.dto.GiaoDichDto;
import org.example.test.model.GiaoDich;
import org.example.test.model.KhachHang;
import org.example.test.service.IGiaoDichService;
import org.example.test.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/", "/transactions"})
public class GiaoDichController {

    private final IGiaoDichService giaoDichService;
    private final IKhachHangService khachHangService;

    @Autowired
    public GiaoDichController(IGiaoDichService giaoDichService, IKhachHangService khachHangService) {
        this.giaoDichService = giaoDichService;
        this.khachHangService = khachHangService;
    }

    /**
     * Màn hình danh sách giao dịch kèm tìm kiếm theo tên khách hàng và loại dịch vụ
     */
    @GetMapping({"", "/list"})
    public String showTransactionList(
            @RequestParam(name = "tenKhachHang", required = false, defaultValue = "") String tenKhachHang,
            @RequestParam(name = "loaiDichVu", required = false, defaultValue = "") String loaiDichVu,
            Model model) {

        List<GiaoDich> danhSachGiaoDich = giaoDichService.search(tenKhachHang, loaiDichVu);

        // Tính toán thống kê nhanh cho Header Stats (VIP Pro Max)
        double tongDoanhSo = danhSachGiaoDich.stream()
                .mapToDouble(GiaoDich::getTongTien)
                .sum();
        double tongDienTich = danhSachGiaoDich.stream()
                .mapToDouble(GiaoDich::getDienTich)
                .sum();

        model.addAttribute("danhSachGiaoDich", danhSachGiaoDich);
        model.addAttribute("tenKhachHang", tenKhachHang);
        model.addAttribute("loaiDichVu", loaiDichVu);
        model.addAttribute("tongSoGiaoDich", danhSachGiaoDich.size());
        model.addAttribute("tongDoanhSo", tongDoanhSo);
        model.addAttribute("tongDienTich", tongDienTich);

        return "transactions/list";
    }

    /**
     * Màn hình thêm mới giao dịch
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("giaoDichDto", new GiaoDichDto());
        model.addAttribute("danhSachKhachHang", khachHangService.findAll());
        return "transactions/create";
    }

    /**
     * Xử lý thêm mới giao dịch với đầy đủ kiểm tra lỗi nghiệp vụ và validate form
     */
    @PostMapping("/create")
    public String processCreate(
            @Valid @ModelAttribute("giaoDichDto") GiaoDichDto giaoDichDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Thực hiện kiểm tra các ràng buộc theo đề bài
        giaoDichService.validateBusinessRules(giaoDichDto, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("danhSachKhachHang", khachHangService.findAll());
            return "transactions/create";
        }

        try {
            giaoDichService.save(giaoDichDto);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Thêm mới giao dịch [" + giaoDichDto.getMaGiaoDich() + "] thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Có lỗi xảy ra khi lưu giao dịch: " + e.getMessage());
        }

        return "redirect:/transactions";
    }

    /**
     * Màn hình xem chi tiết giao dịch
     */
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<GiaoDich> giaoDichOpt = giaoDichService.findById(id);
        if (giaoDichOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Không tìm thấy thông tin giao dịch với mã: " + id);
            return "redirect:/transactions";
        }

        model.addAttribute("giaoDich", giaoDichOpt.get());
        return "transactions/detail";
    }

    /**
     * Xử lý xóa giao dịch sau khi người dùng xác nhận "Có"
     */
    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        boolean deleted = giaoDichService.delete(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Đã xóa thành công giao dịch mã [" + id + "]!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Không tìm thấy giao dịch [" + id + "] để xóa!");
        }
        return "redirect:/transactions";
    }
}
