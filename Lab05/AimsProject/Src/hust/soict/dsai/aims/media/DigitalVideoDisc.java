package AimsProject.Src.hust.soict.dsai.aims.media;
import AimsProject.Src.hust.soict.dsai.aims.exception.PlayerException;
public class DigitalVideoDisc extends Disc implements Playable {

    // Constructor 1 tham số: Title
    public DigitalVideoDisc(String title) {
        super(); // Gọi constructor mặc định của Disc -> Media
        this.setTitle(title);
    }

    // Constructor 3 tham số: Title, Category, Cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    // Constructor 4 tham số: Director, Category, Title, Cost
    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setDirector(director);
        this.setCost(cost);
    }

    // Constructor đầy đủ: Title, Category, Director, Length, Cost
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        // Sử dụng constructor 5 tham số của lớp cha Disc
        super(title, category, director, length, cost);
    }
    
    // Nếu bạn muốn quản lý cả ID từ lớp Media (khuyên dùng)
    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, director, length);
    }
    
    // Thực thi phương thức play() từ interface Playable
    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: DVD \"" + this.getTitle() + "\" has non-positive length!");
        }
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    // Ghi đè phương thức toString()
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    // Phương thức kiểm tra tiêu đề khớp (không bắt buộc nhưng tốt cho chức năng tìm kiếm)
    public boolean isMatch(String title) {
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
}