package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import  main.mainButtons.buttonBuilder;

public class Finalon extends Application {
    private Background background(String color){
        return new Background( new BackgroundFill( Color.web( color ), CornerRadii.EMPTY, Insets.EMPTY ) );
    }

    private HBox getHeader(){
        HBox header = new HBox(10);
        header.setMaxHeight(180);
        header.setPrefHeight(180);
        header.setFillHeight(false);
        header.setBackground(background("#0095CD"));
        return header;
    }

    @Override
    public void start(Stage primaryStage) {


        BorderPane root = new BorderPane();

        root.setTop(getHeader());

        VBox pane2 = new VBox();
        pane2.setBackground(background("#222C3C"));
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.setSpacing(10);

        buttonBuilder button = new buttonBuilder();
        pane2.getChildren().add(button.addCompanyButton());
        pane2.getChildren().add(button.addSettingsButton());
        pane2.getChildren().add(button.addHelpButton());
        pane2.getChildren().add(button.addExitButton());
        root.setLeft(pane2);




        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Financial Statement Analysis Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}