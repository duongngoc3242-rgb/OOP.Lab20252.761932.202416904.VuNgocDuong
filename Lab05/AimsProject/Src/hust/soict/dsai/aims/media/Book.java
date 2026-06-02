package AimsProject.Src.hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    
    private List<String> authors = new ArrayList<String>();

    public Book() {
        super(); // Gọi constructor của Media [cite: 62, 73]
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost); // Sử dụng constructor của Media để gán giá trị [cite: 89]
    }

    // Các phương thức riêng của Book
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Tác giả đã tồn tại trong danh sách.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        } else {
            System.out.println("Không tìm thấy tác giả để xóa.");
        }
    }

    @Override
    public String toString() {
        return "Book: " + getTitle() + " - " + getCategory() + " - Authors: " + authors + " - Cost: " + getCost();
    }
}