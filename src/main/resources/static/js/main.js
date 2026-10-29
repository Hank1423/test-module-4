// Common / global JavaScript

// Tự đóng thông báo (thành công / lỗi) sau 6 giây
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.alert-flash.alert-dismissible').forEach(function (el) {
        setTimeout(function () {
            if (!document.body.contains(el)) return;
            bootstrap.Alert.getOrCreateInstance(el).close();
        }, 6000);
    });
});
