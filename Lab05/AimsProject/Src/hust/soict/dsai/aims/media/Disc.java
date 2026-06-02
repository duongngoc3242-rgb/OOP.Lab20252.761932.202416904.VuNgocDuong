package AimsProject.Src.hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    // Getter và Setter
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    // Constructor mặc định
    public Disc() {
        super();
    }

    // Constructor này để giải quyết lỗi "undefined" của bạn
    // Thứ tự: title, category, director, length, cost
    public Disc(String title, String category, String director, int length, float cost) {
        super(); // Gọi constructor mặc định của Media
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        this.director = director;
        this.length = length;
    }

    // Constructor có ID (nếu bạn muốn quản lý ID thủ công)
    public Disc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }
}