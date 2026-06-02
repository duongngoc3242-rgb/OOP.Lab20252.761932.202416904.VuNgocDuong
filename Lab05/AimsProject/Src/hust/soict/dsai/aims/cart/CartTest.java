package AimsProject.Src.hust.soict.dsai.aims.cart;
import AimsProject.Src.hust.soict.dsai.aims.media.DigitalVideoDisc;
import AimsProject.Src.hust.soict.dsai.aims.media.*;

public class CartTest {
    public static void main(String[] args) {
        // 1. Khởi tạo một giỏ hàng mới
        Cart cart = new Cart();

        // 2. Tạo các đối tượng Media đa hình
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", 87);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, "George Lucas", 124);
        
        // Tạo thêm CD và Book để kiểm tra tính đa hình của ObservableList<Media>
        CompactDisc cd = new CompactDisc(3, "Thriller", "Pop", 15.00f, "Quincy Jones", 42, "Michael Jackson");
        Book book = new Book(4, "Java Programming", "Education", 29.99f);

        // 3. Thêm các sản phẩm vào giỏ hàng
        cart.addMedia(dvd1);
        cart.addMedia(dvd2);
        cart.addMedia(cd);
        cart.addMedia(book);

        // 4. Kiểm tra phương thức in danh sách giỏ hàng
        cart.print();

        // 5. Kiểm tra tính năng tìm kiếm theo ID
        System.out.println("\n--- Testing Search by ID ---");
        cart.searchById(2); // Tìm thấy Star Wars
        cart.searchById(99); // Không tìm thấy

        // 6. Kiểm tra tính năng tìm kiếm theo Title (không phân biệt hoa thường)
        System.out.println("\n--- Testing Search by Title ---");
        cart.searchByTitle("lion"); // Tìm thấy The Lion King
        cart.searchByTitle("Aladdin"); // Không tìm thấy
    }
}