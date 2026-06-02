package AimsProject.Src.hust.soict.dsai.aims.store;

import AimsProject.Src.hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    // Sử dụng ArrayList<Media> để chứa được cả Book, DVD và CD
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    // Phương thức thêm sản phẩm vào cửa hàng
    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media has been added to the store: " + media.getTitle());
        } else {
            System.out.println("The media is already in the store.");
        }
    }

    // Phương thức xóa sản phẩm khỏi cửa hàng
    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media has been removed from the store: " + media.getTitle());
        } else {
            System.out.println("The media is not found in the store.");
        }
    }

    // Phương thức để xem toàn bộ sản phẩm trong cửa hàng dưới dạng CLI console
    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in store:");
        if (itemsInStore.isEmpty()) {
            System.out.println("[Store is currently empty]");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
            }
        }
        System.out.println("***************************************************");
    }

    // Phương thức hỗ trợ tìm kiếm sản phẩm trong cửa hàng theo tiêu đề (Không phân biệt hoa thường)
    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }

    // Getter chuẩn để lấy danh sách sản phẩm phục vụ hiển thị CLI
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}