package AimsProject.Src.hust.soict.dsai.aims.screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import AimsProject.Src.hust.soict.dsai.aims.media.Media;
import AimsProject.Src.hust.soict.dsai.aims.store.Store;
import AimsProject.Src.hust.soict.dsai.aims.cart.Cart; // ĐÃ THÊM: Import lớp Cart để sử dụng

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart; // ĐÃ THÊM: Thuộc tính cart để lưu trữ giỏ hàng dùng chung

    // ĐÃ SỬA: Hàm khởi tạo nhận thêm đối tượng Cart
    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart; // ĐÃ THÊM: Gán giá trị giỏ hàng được truyền vào từ hàm main (Aims.java)
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setSize(1024, 768);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menu.add(new JMenuItem("View store"));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS Store");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        // Lấy danh sách hàng hóa có trong Store
        ArrayList<Media> mediaInStore = store.getItemsInStore(); 

        center.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < Math.min(9, mediaInStore.size()); i++) {
            // ĐÃ SỬA: Truyền thêm thuộc tính `this.cart` vào đây để MediaStore nhận được giỏ hàng
            MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart);
            center.add(cell);
        }
        return center;
    }
}