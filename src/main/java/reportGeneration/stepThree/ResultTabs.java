package reportGeneration.stepThree;

import entities.Formula;
import globalReusables.VBoxTryCatchWrap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import reportGeneration.interpreter.Interprter;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.wordExport.WordExport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultTabs {

    private Interprter interprter = new Interprter();
    private Map<String, Tab> tabsArr = new HashMap<>();
    private VBox throbber = getThrobber();


    private VBox getThrobber() {
        Image img = new Image("image/loading.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        VBox vBox = new VBox(iv);
        vBox.setPrefHeight(730);
        vBox.setFillWidth(true);
        vBox.setAlignment(Pos.TOP_CENTER);
        return vBox;
    }

    private ArrayList<Tab> loopSections() {
        ArrayList<Tab> tabs = new ArrayList<Tab>();
        ObservableList<Formula> sections = FormulaStorage.getSections();
        int number = 1;
        int counter = 0;
        Formula formula;
        String name;
        Tab tab;
        for (int i = 0; i < sections.size(); i++) {
            formula = sections.get(i);
            String code = formula.getShortName();
            name = number + ". " + formula.getName();
            number++;
            counter = counter + 100;
            final int num = counter;
            VBox vBox = new VBoxTryCatchWrap(() -> interprter.getReport(code, num)).get();
            if(vBox.getChildren().size() > 0){
                tab = new Tab(name);
                tab.setContent(vBox);
                tabs.add(tab);
                tabsArr.put(code, tab);
            }
        }
        return tabs;
    }

    public VBox getTabs() {

        VBox vBox = new VBox();
        HBox hBox = new HBox(20);
        hBox.setVisible(false);
        hBox.getChildren().addAll(exportBtn());
        vBox.getChildren().addAll(hBox, throbber);
        TabPane tabs = new TabPane();
        tabs.setStyle("-fx-padding: 10px 0 0 0;");
        Task listLoader = new Task<ArrayList<Tab>>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    tabs.getTabs().addAll(getValue());
                    vBox.getChildren().remove(throbber);
                    hBox.setVisible(true);

                });
                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected ArrayList<Tab> call() throws Exception {
                ArrayList<Tab> tabs = new ArrayList<>();
                ArrayList<Tab> sections = loopSections();
                for (Tab formulaTab : sections) {
                    tabs.add(formulaTab);
                }
                return tabs;
            }
        };

        Thread loadingThread = new Thread(listLoader, "list-loader");
        loadingThread.setDaemon(true);
        loadingThread.start();
        vBox.getChildren().add(tabs);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private HBox exportBtn() {
        HBox hbox = new HBox(30);
        Label label = new Label();
        label.getStyleClass().add("docx-saved-label");
        Button btn = new Button("Export to Docx");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    WordExport export = new WordExport();
                    export.exportDoc();
                    label.setText("Export file successfully saved!");
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(5000),
                            ae -> label.setText("")));
                    timeline.play();
                } catch (Exception exception) {
                    System.out.println("Error while saving file: " + exception.getMessage());
                }
            }
        });
        hbox.getChildren().addAll(btn, label);
        return hbox;
    }


}
