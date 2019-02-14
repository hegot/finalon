package finalonWindows;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {

    public void updateImages(final Image img, int height) {
        ImageView iv = new ImageView(img);
        iv.setFitHeight(height);
        this.getChildren().add(iv);

        super.setGraphic(iv);
    }
}