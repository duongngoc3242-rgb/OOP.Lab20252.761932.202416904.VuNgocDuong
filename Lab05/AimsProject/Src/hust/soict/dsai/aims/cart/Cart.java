package AimsProject.Src.hust.soict.dsai.aims.cart;
import java.util.Collections;

import AimsProject.Src.hust.soict.dsai.aims.media.Media;
import AimsProject.Src.hust.soict.dsai.aims.media.MediaComparatorByCostTitle;
import AimsProject.Src.hust.soict.dsai.aims.media.MediaComparatorByTitleCost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    
    // Đổi từ mảng DVD[] sang ObservableList<Media> để phục vụ JavaFX TableView
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    // Phương thức bắt buộc phải có để CartScreenController lấy dữ liệu đổ vào bảng
    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    // Thêm một item Media (Thay thế addDigitalVideoDisc cũ)
    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            if (!itemsOrdered.contains(media)) {
                itemsOrdered.add(media);
                System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
            } else {
                System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
            }
        } else {
            System.out.println("The cart is full! Cannot add more items.");
        }
    }

    // Xóa một item Media (Thay thế removeDigitalVideoDisc cũ)
    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" is not found in the cart.");
        }
    }

    // Tính tổng tiền giỏ hàng dựa trên danh sách ObservableList mới
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // In thông tin giỏ hàng ra Console (Cập nhật theo danh sách mới)
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        int i = 1;
        for (Media media : itemsOrdered) {
            System.out.println(i + ". " + media.toString());
            i++;
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    public void sortByTitleCost() {
        
        Collections.sort(this.itemsOrdered, new MediaComparatorByTitleCost());
        
    }

    public void sortByCostTitle() {
        Collections.sort(this.itemsOrdered, new MediaComparatorByCostTitle());
    
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No media found with ID: " + id);
        }
    }    

    // Tìm kiếm phần tử theo Title (Sử dụng hàm isMatch có sẵn của lớp Media)
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }
}