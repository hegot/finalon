package reportGeneration.stepThree;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Interprter;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.wordExport.WordExport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultTabs {

    private Interprter interprter = new Interprter();
    private Map<String, Tab> tabsArr = new HashMap<>();
    private ImageView throbber = getThrobber();


    private ImageView getThrobber() {
        Image img = new Image("image/loading.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        return iv;
    }

    private ArrayList<Tab> loopSections() {
        ArrayList<Tab> tabs = new ArrayList<Tab>();
        ObservableList<Formula> sections = FormulaStorage.getSections();
        int number = 1;
        int counter = 0;
        for (int i = 0; i < sections.size(); i++) {
            Formula formula = sections.get(i);
            String code = formula.getShortName();
            String name = number + ". " + formula.getName();
            number++;
            Tab tab = new Tab(name);
            tabs.add(tab);
            counter = counter + 100;
            final int num = counter;
            System.out.println(code);
            VBox vBox = interprter.getReport(code, num);
            if (vBox != null) {
                tab.setContent(vBox);
            }
            tabsArr.put(code, tab);
        }
        return tabs;
    }

    public VBox getTabs() {

        VBox vBox = new VBox();
        HBox hBox = new HBox(20);
        hBox.setVisible(false);
        hBox.getChildren().addAll(exportBtn(), SaveReport.getPane());
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

    private Button exportBtn() {
        Button btn = new Button("Export Doc");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    WordExport export = new WordExport();
                    export.exportDoc();
                } catch (Exception exception) {
                    System.out.println("Error while saving file: " + exception.getMessage());
                }
            }
        });
        return btn;
    }


}
