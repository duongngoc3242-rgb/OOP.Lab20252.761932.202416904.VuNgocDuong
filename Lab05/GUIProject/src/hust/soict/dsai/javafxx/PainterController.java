package hust.soict.dsai.javafxx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadio;

    @FXML
    private RadioButton eraserRadio;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color strokeColor = Color.BLACK;
        int radius = 4;
        
        // Nếu tùy chọn Eraser được chọn, đổi màu vẽ thành TRẮNG trùng với màu nền
        if (eraserRadio.isSelected()) {
            strokeColor = Color.WHITE;
            radius = 16; // Tăng kích thước bán kính vòng tròn để dễ tẩy xóa
        }
        
        Circle newCircle = new Circle(event.getX(), event.getY(), radius, strokeColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}