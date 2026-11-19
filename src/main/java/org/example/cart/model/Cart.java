package org.example.cart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<LineItem> items;

    // 1. Luôn khởi tạo ArrayList trong Constructor để tránh NullPointerException
    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<LineItem> items) {
        this.items = items;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    // 2. Sửa addItem: Duyệt tìm xem đã có chưa, nếu chưa có mới add sau vòng lặp
    public void addItem(LineItem item) {
        String code = item.getProduct().getProductCode();
        int quantity = item.getQuantity();

        for (LineItem c : items) {
            if (c.getProduct().getProductCode().equals(code)) {
                c.setQuantity(c.getQuantity() + quantity);
                return; // Đã tìm thấy và cộng dồn số lượng -> Thoát hàm luôn
            }
        }

        // Nếu duyệt hết danh sách mà chưa có -> Mới thêm vào
        items.add(item);
    }

    // 3. Sửa removeItem: Dùng removeIf để xóa an toàn, không bị lỗi vòng lặp
    public void removeItem(LineItem item) {
        String code = item.getProduct().getProductCode();
        items.removeIf(c -> c.getProduct().getProductCode().equals(code));
    }

    // 4. Sửa updateItem: Cập nhật số lượng, nếu quantity <= 0 thì tự xóa
    public void updateItem(int quantity, LineItem item) {
        String code = item.getProduct().getProductCode();
        if (quantity <= 0) {
            removeItem(item);
            return;
        }

        for (LineItem c : items) {
            if (c.getProduct().getProductCode().equals(code)) {
                c.setQuantity(quantity);
                break;
            }
        }
    }
}