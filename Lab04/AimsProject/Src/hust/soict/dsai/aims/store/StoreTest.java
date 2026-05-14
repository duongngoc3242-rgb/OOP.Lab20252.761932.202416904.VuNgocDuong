package AimsProject.Src.hust.soict.dsai.aims.store;

import AimsProject.Src.hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars");
        
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        
        store.removeDVD(dvd1);
        // Thử xóa một đĩa không tồn tại để test logic
        store.removeDVD(dvd1);
    }
}