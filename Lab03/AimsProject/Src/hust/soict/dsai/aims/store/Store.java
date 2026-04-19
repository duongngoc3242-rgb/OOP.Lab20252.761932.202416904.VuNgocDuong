package AimsProject.Src.hust.soict.dsai.aims.store;

import AimsProject.Src.hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Store {
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[100];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc dvd) {
        if (qtyInStore < 100) {
            itemsInStore[qtyInStore] = dvd;
            qtyInStore++;
            System.out.println("DVD đã được thêm vào kho.");
        } else {
            System.out.println("Kho đã đầy!");
        }
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].equals(dvd)) {
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("DVD đã được xóa khỏi kho.");
                return;
            }
        }
        System.out.println("Không tìm thấy DVD này trong kho.");
    }
}