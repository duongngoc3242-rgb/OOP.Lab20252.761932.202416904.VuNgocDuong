package AimsProject.Src.hust.soict.dsai.aims.store;

import AimsProject.Src.hust.soict.dsai.aims.media.*;

public class StoreTest {
    public static void main(String[] args) {
        // 1. Khởi tạo một cửa hàng mới
        Store store = new Store();

        // 2. Tạo các sản phẩm thử nghiệm
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", 87);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, "George Lucas", 124);
        Book book = new Book(3, "Effective Java", "Education", 45.00f);

        // 3. Kiểm tra tính năng thêm sản phẩm vào kho
        System.out.println("--- Testing Add to Store ---");
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book);

        // 4. Kiểm tra tính năng xóa sản phẩm khỏi kho
        System.out.println("\n--- Testing Remove from Store ---");
        store.removeMedia(dvd2); // Xóa Star Wars
        store.removeMedia(dvd2); // Thử xóa lại lần nữa để test thông báo lỗi
    }
}