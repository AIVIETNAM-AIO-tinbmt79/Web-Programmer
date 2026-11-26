package murach.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<LineItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    // Thêm sản phẩm; nếu đã tồn tại thì tăng số lượng
    public void addItem(LineItem newItem) {
        String code = newItem.getProduct().getProductCode();

        for (LineItem item : items) {
            if (item.getProduct().getProductCode().equals(code)) {
                item.setQuantity(
                        item.getQuantity() + newItem.getQuantity()
                );
                return;
            }
        }

        items.add(newItem);
    }

    // Cập nhật số lượng; số lượng <= 0 thì xóa
    public void updateItem(String code, int quantity) {
        for (LineItem item : items) {
            if (item.getProduct().getProductCode().equals(code)) {
                if (quantity <= 0) {
                    removeItem(code);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    // Xóa sản phẩm theo mã
    public void removeItem(String code) {
        items.removeIf(
                item -> item.getProduct().getProductCode().equals(code)
        );
    }

    // Tính tổng tiền toàn bộ giỏ hàng
    public double getTotal() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }
}