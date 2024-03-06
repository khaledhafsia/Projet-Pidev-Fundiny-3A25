package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class StatistiqueCollaborationController {

    @FXML
    private PieChart pieChart;

    public void setData(ObservableList<PieChart.Data> data) {
        pieChart.setData(data);
    }
}
