package main.mainButtons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import main.ImageButton;

public class buttonBuilder {


    public ImageButton addCompanyButton(){
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("resource/image/add.png"), new Image("resource/image/add1.png"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Accepted");
            }
        });
        return btn;
    }

    public ImageButton addSettingsButton(){
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("resource/image/settings.png"), new Image("resource/image/settings1.png"));
        return btn;
    }

    public ImageButton addHelpButton(){
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("resource/image/info.png"), new Image("resource/image/info1.png"));
        return btn;
    }

    public ImageButton addExitButton(){
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("resource/image/exit.png"), new Image("resource/image/exit1.png"));
        return btn;
    }

}
