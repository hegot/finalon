package finalonWindows;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Button {

    public void updateImages(final Image selected, final Image unselected, int height) {
        ImageView iv = new ImageView(selected);
        iv.setFitHeight(height);
        this.getChildren().add(iv);

        iv.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(unselected);
                iv.setFitHeight(height);
            }
        });
        iv.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(selected);
                iv.setFitHeight(height);
            }
        });

        super.setGraphic(iv);
    }
}