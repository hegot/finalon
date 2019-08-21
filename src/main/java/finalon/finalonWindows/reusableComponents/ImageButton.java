package finalon.finalonWindows.reusableComponents;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {
    public ImageButton(String imgPath, int height) {
        super();
        Image img = new Image(imgPath);
        ImageView iv = new ImageView(img);
        iv.setFitHeight(height);
        this.getChildren().add(iv);
        super.setGraphic(iv);
    }
}