package reportGeneration.interpreter.ReusableComponents;

import database.setting.DbSettingHandler;
import globalReusables.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartBase {

    private void displayLabelForData(XYChart.Data<String, Double> data) {
        final Node node = data.getNode();
        final Text dataText = new Text(Formatter.finalNumberFormat(data.getYValue()) + "");
        node.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });

        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                dataText.setLayoutX(
                        Math.round(
                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                        )
                );
                dataText.setLayoutY(
                        Math.round(
                                bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                        )
                );
            }
        });
    }

    protected BarChart<String, Number> getChart(List<Double> values) {
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(
                new CategoryAxis(),
                yAxis
        );
        yAxis.setLabel("Value");
        bc.setStyle("-fx-font-size: 12px;");
        bc.setMinHeight(600);

        Double max = Collections.max(values);
        Double min = Collections.min(values);

        NumberAxis axis = (NumberAxis) bc.getYAxis();
        axis.setAutoRanging(false);
        Double range = 10.00;
        for (int i = 0; i < 1000000; i += 10) {
            if (max > i * 5) {
                range = i * 1.0;
            }
        }
        Double k = 1.0;
        if (max > 10) {
            k = 10.0;
        }
        if (max > 100) {
            k = 100.0;
        }
        if (max > 1000) {
            k = 100.0;
        }
        if (max > 10000) {
            k = 1000.0;
        }
        if (max > 100000) {
            k = 100000.0;
        }
        if (max > 1000000) {
            k = 1000000.0;
        }
        axis.setTickUnit(k);
        Double upper = max + range;
        upper = (Math.ceil(upper / k)) * k;
        axis.setUpperBound(upper);
        if (min > 0) {
            axis.setLowerBound(0);
        } else {
            axis.setLowerBound(min - range);
        }

        return bc;
    }

    protected XYChart.Series getSeries(String label, ObservableMap<String, Double> values) {
        XYChart.Series series = new XYChart.Series();
        series.setName(label);
        series.getData().addAll(getData(values));
        return series;
    }

    private ArrayList<XYChart.Data> getData(ObservableMap<String, Double> values) {
        ArrayList<XYChart.Data> data = new ArrayList<XYChart.Data>();
        if (values.size() > 1) {
            String date;
            for (String period : Periods.getPeriodArr()) {
                date = Formatter.formatDate(period);

                XYChart.Data<String, Double> dataPiece = new XYChart.Data(date, values.get(period));
                if (values.get(period) != null) {
                    dataPiece.nodeProperty().addListener(new ChangeListener<Node>() {
                        @Override
                        public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
                            if (node != null) {
                                displayLabelForData(dataPiece);
                            }
                        }
                    });
                    data.add(dataPiece);
                }
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(data);
        }

        return data;
    }
}
