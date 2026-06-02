package AimsProject.Src.hust.soict.dsai.aims.screen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import AimsProject.Src.hust.soict.dsai.aims.cart.Cart;
import AimsProject.Src.hust.soict.dsai.aims.media.*;
import AimsProject.Src.hust.soict.dsai.aims.exception.PlayerException;

public class CartScreenController {
    private Cart cart;
    private FilteredList<Media> filteredData;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;

    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private Label lblTotal;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredData = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredData);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalCost();

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if (newValue != null) {
                    btnRemove.setVisible(true);
                    btnPlay.setVisible(newValue instanceof Playable);
                } else {
                    btnPlay.setVisible(false);
                    btnRemove.setVisible(false);
                }
            }
        });

        // Lắng nghe thay đổi ô Filter để lọc dữ liệu thời gian thực
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(newValue);
        });
    }

    private void showFilteredMedia(String keyword) {
        filteredData.setPredicate(media -> {
            if (keyword == null || keyword.isEmpty()) return true;
            String lowerCaseFilter = keyword.toLowerCase();

            if (radioBtnFilterTitle.isSelected() && media.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (radioBtnFilterId.isSelected() && String.valueOf(media.getId()).contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        updateTotalCost();
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        try {
            ((Playable) media).play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Now playing: " + media.getTitle());
            alert.showAndWait();
        } catch (PlayerException e) {
            // Hiển thị hộp thoại báo lỗi Alert dạng ERROR của JavaFX khi độ dài <= 0 (Mục 14)
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Playing Media");
            alert.setHeaderText("Non-positive media length detected");
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        lblTotal.setText(String.format("%.2f $", cart.totalCost()));
    }
}