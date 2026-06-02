package AimsProject.Src.hust.soict.dsai.aims;

import AimsProject.Src.hust.soict.dsai.aims.cart.Cart;
import AimsProject.Src.hust.soict.dsai.aims.exception.PlayerException;
import AimsProject.Src.hust.soict.dsai.aims.store.Store;
import AimsProject.Src.hust.soict.dsai.aims.media.*;
import AimsProject.Src.hust.soict.dsai.aims.screen.CartScreen;
import AimsProject.Src.hust.soict.dsai.aims.screen.StoreScreen;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo một số dữ liệu mẫu để test
        initData();
        
        // Mở song song giao diện đồ họa (GUI) cho Lab 05
        System.out.println("Launching AIMS GUIs...");
        new StoreScreen(store, cart);
        new CartScreen(cart);
        
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: viewCart(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    // --- CÁC PHƯƠNG THỨC HIỂN THỊ MENU ---
    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }
    
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    // --- CÁC HÀM XỬ LÝ LOGIC ---
    public static void viewStore() {
        store.printStore();
        int choice;
        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    Media m = store.searchByTitle(title);
                    if (m != null) {
                        System.out.println(m.toString());
                        System.out.println("1. Add to cart | 0. Back");
                        if(scanner.nextInt() == 1) cart.addMedia(m);
                    } else System.out.println("Not found!");
                    break;
                case 2:
                    System.out.print("Enter title to add: ");
                    Media mAdd = store.searchByTitle(scanner.nextLine());
                    if (mAdd != null) cart.addMedia(mAdd);
                    break;
                case 3:
                    System.out.print("Enter title to play: ");
                    Media mPlay = store.searchByTitle(scanner.nextLine());
                    if (mPlay instanceof Playable) {
                        try {
                            ((Playable) mPlay).play();
                        } catch (PlayerException e) {
                            System.err.println(e.getMessage()); 
                        }
                    } else System.out.println("This media cannot be played!");
                    break;
                case 4: viewCart(); break;
            }
        } while (choice != 0);
    }

    public static void viewCart() {
        int choice;
        do {
            cart.print(); 
            cartMenu();   
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1: 
                    System.out.println("Filter options: 1. By ID | 2. By Title");
                    int filterType = scanner.nextInt();
                    scanner.nextLine();
                    if (filterType == 1) {
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        cart.searchById(id);
                    } else {
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        cart.searchByTitle(title);
                    }
                    break;

                case 2: 
                    System.out.println("Sort options: 1. By Title-Cost | 2. By Cost-Title");
                    int sortType = scanner.nextInt();
                    if (sortType == 1) {
                        cart.sortByTitleCost();
                    } else {
                        cart.sortByCostTitle();
                    }
                    cart.print();
                    break;

                case 3: 
                    System.out.print("Enter the title of the media to remove: ");
                    String titleToRemove = scanner.nextLine();
                    Media mRem = store.searchByTitle(titleToRemove); 
                    if (mRem != null) {
                        cart.removeMedia(mRem);
                    } else {
                        System.out.println("Media not found!");
                    }
                    break;

                case 4: 
                    System.out.print("Enter title to play: ");
                    String titleToPlay = scanner.nextLine();
                    Media mPlayCart = store.searchByTitle(titleToPlay);
                    if (mPlayCart instanceof Playable) {
                        try {
                            ((Playable) mPlayCart).play();
                        } catch (PlayerException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        System.out.println("This media type is not playable!");
                    }
                    break;

                case 5: 
                    if (cart.getItemsOrdered().size() > 0) {
                        System.out.println("An order has been created. The cart is now empty.");
                        cart = new Cart(); 
                    } else {
                        System.out.println("Your cart is empty. Cannot place order.");
                    }
                    break;

                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void initData() {
        // ĐÃ SỬA: Khớp chuẩn theo constructor 6 tham số của bạn: (id, title, category, director, length, cost)
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);
        
        Book book1 = new Book(2, "Java Programming", "Tech", 50.0f);
        store.addMedia(book1);
        
     
        // Thứ tự đúng có thể là: id, title, category, director, length, cost, artist
        CompactDisc cd1 = new CompactDisc(3, "Thriller", "Pop", "Quincy Jones", 42, 15.00f, "Michael Jackson");
        store.addMedia(cd1);
    }
    
    public static void updateStore() {
        int choice;
        do {
            System.out.println("============================");
            System.out.println("Update Store Options: ");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("============================");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Select media type: 1. DVD | 2. Book | 3. CD");
                int type = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Category: ");
                String category = scanner.nextLine();
                System.out.print("Enter Cost: ");
                float cost = scanner.nextFloat();
                scanner.nextLine();

                if (type == 1) { // DVD
                    System.out.print("Enter Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter Length: ");
                    int length = scanner.nextInt();
                    
                    // ĐÃ SỬA: Truyền đúng thứ tự (id, title, category, director, length, cost)
                    store.addMedia(new DigitalVideoDisc(id, title, category, director, length, cost));
                } 
                else if (type == 2) { 
                    store.addMedia(new Book(id, title, category, cost));
                } 
                else if (type == 3) { 
                    System.out.print("Enter Artist: ");
                    String artist = scanner.nextLine();
                    store.addMedia(new CompactDisc(id, title, category, "", 0, cost, artist));
                }
            } 
            else if (choice == 2) {
                System.out.print("Enter the title of the media to remove from Store: ");
                String titleToRemove = scanner.nextLine();
                Media m = store.searchByTitle(titleToRemove);
                if (m != null) {
                    store.removeMedia(m);
                } else {
                    System.out.println("Media not found in store.");
                }
            }
        } while (choice != 0);
    }
}