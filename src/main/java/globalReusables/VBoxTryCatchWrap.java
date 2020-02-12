package globalReusables;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.function.Supplier;

public class VBoxTryCatchWrap implements Supplier<VBox> {

    private Supplier<VBox> transformer;

    public VBoxTryCatchWrap(Supplier<VBox> transformer) {
        this.transformer = transformer;
    }

    @Override
    public VBox get() {
        VBox vBox = new VBox();
        try {
            vBox = transformer.get();
        } catch (Exception e) {
            Label err = new Label();
            err.setText(e.getMessage());
            vBox.getChildren().add(err);
        }
        return vBox;
    }
}