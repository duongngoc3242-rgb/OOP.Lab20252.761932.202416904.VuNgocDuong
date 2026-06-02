package AimsProject.Src.hust.soict.dsai.aims.screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import AimsProject.Src.hust.soict.dsai.aims.cart.Cart;
import AimsProject.Src.hust.soict.dsai.aims.media.*;
import AimsProject.Src.hust.soict.dsai.aims.exception.PlayerException;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart; // Đfont THÊM: Khai báo thuộc tính cart để sử dụng trong toàn bộ lớp

    // Đfont SỬA: Hàm khởi tạo nhận thêm tham số Cart để tương tác với giỏ hàng
    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart; // Đfont THÊM: Gán giá trị cart được truyền vào
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Thiết lập hiển thị Tiêu đề sản phẩm
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Thiết lập hiển thị Giá sản phẩm
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Container chứa các nút bấm hành động
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // 1. Tạo nút Add to cart và gắn sự kiện click chuột luôn tại đây
        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thêm sản phẩm vào giỏ hàng
                cart.addMedia(media); 
                
                // Hiển thị hộp thoại thông báo thêm thành công lên màn hình GUI
                JOptionPane.showMessageDialog(null, "Added \"" + media.getTitle() + "\" to cart!", "Cart Update", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        container.add(btnAddToCart);

        // 2. Nếu sản phẩm có tính năng phát (Playable như CD, DVD) thì thêm nút Play
        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            container.add(btnPlay);
            
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ((Playable) media).play();
                        // Hiển thị hộp thoại khi chạy nhạc/phim thành công trên giao diện
                        JOptionPane.showMessageDialog(null, "Playing: " + media.getTitle(), "Media Player", JOptionPane.INFORMATION_MESSAGE);
                    } catch (PlayerException ex) {
                        // Hiển thị thông báo lỗi nếu thời lượng hoặc dữ liệu media không hợp lệ
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Playing Media", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        // Sắp xếp các thành phần giao diện theo chiều dọc
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        // Tạo khung viền bao quanh mỗi ô sản phẩm
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}