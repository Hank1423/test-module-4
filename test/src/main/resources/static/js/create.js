// Create page - total price preview and min date
document.addEventListener('DOMContentLoaded', function () {
    // Đặt min date là ngày mai
    const dateInput = document.getElementById('ngayGiaoDich');
    if (dateInput) {
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        const yyyy = tomorrow.getFullYear();
        const mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
        const dd = String(tomorrow.getDate()).padStart(2, '0');
        dateInput.min = `${yyyy}-${mm}-${dd}`;
    }

    // Realtime Calculator
    const donGiaInput = document.getElementById('donGia');
    const dienTichInput = document.getElementById('dienTich');
    const preview = document.getElementById('previewTongTien');

    function updatePreview() {
        const donGia = parseFloat(donGiaInput.value) || 0;
        const dienTich = parseFloat(dienTichInput.value) || 0;
        const total = donGia * dienTich;
        preview.textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(total);
    }

    if (donGiaInput && dienTichInput) {
        donGiaInput.addEventListener('input', updatePreview);
        dienTichInput.addEventListener('input', updatePreview);
        updatePreview();
    }
});
