package AimsProject.Src.hust.soict.dsai.aims.media;

import java.util.Comparator;

public abstract class Media {

    // Các trường dữ liệu chung cho tất cả các loại Media 
    private int id;
    private String title;
    private String category;
    private float cost;

    // Các Comparator để sắp xếp Media 
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    // Constructor mặc định
    public Media() {
    }

    // Constructor với đầy đủ tham số (khuyên dùng để khởi tạo dữ liệu nhanh)
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    

    // Các phương thức Accessor (Getters) và Mutator (Setters) 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
   @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Media)) return false;
        Media other = (Media) obj;
        if (this.title == null) return other.title == null;
        return this.title.equalsIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "Media [ID=" + id + ", Title=" + title + ", Category=" + category + ", Cost=" + cost + "$]";
    }
   
   
    public boolean isMatch(String title) {
        if (this.title == null || title == null) return false;
        return this.title.toLowerCase().contains(title.toLowerCase());
    }
    
}