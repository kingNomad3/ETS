package com.example.labo4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingSettingsWindow extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Paramètres de tri");

        // ✅ Title Label (Bold and Underlined)
        Label title = new Label("Paramètres pour la visualisation d'un tri");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        title.setUnderline(true);

        // ✅ GridPane for form elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // ✅ Algorithm selection
        Label algoLabel = new Label("Algorithme de tri :");
        ComboBox<String> algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll("QuickSort", "MergeSort");
        algorithmSelector.setValue("MergeSort");

        // ✅ Input field for numbers
        Label inputLabel = new Label("Collection d'entiers à trier :");
        TextField inputField = new TextField("50,87,56,12,75,100,20,34,9");
        inputField.setPrefWidth(250);
        inputField.setPrefHeight(30);

        // ✅ Speed selection
        Label speedLabel = new Label("Vitesse de la simulation :");
        ComboBox<String> speedSelector = new ComboBox<>();
        speedSelector.getItems().addAll("Lent", "Normal", "Rapide");
        speedSelector.setValue("Rapide");

        // ✅ Add elements to the grid
        grid.add(algoLabel, 0, 0);
        grid.add(algorithmSelector, 1, 0);
        grid.add(inputLabel, 0, 1);
        grid.add(inputField, 1, 1);
        grid.add(speedLabel, 0, 2);
        grid.add(speedSelector, 1, 2);

        // ✅ Buttons
        Button applyButton = new Button("Appliquer");
        Button cancelButton = new Button("Annuler");
        Button okButton = new Button("OK");

        // 🎯 Button actions
        applyButton.setOnAction(e -> openSortingVisualization(
                algorithmSelector.getValue(),
                inputField.getText(),
                speedSelector.getValue()
        ));
        cancelButton.setOnAction(e -> primaryStage.close());

        // ✅ Create an HBox for the buttons
        HBox buttonBox = new HBox(15); // 15px spacing between buttons
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0)); // Add top margin
        buttonBox.getChildren().addAll(okButton, applyButton, cancelButton);

        // ✅ **Keep Only One VBox layout**
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_LEFT); // Align all elements to the left
        layout.getChildren().addAll(title, grid, buttonBox); // Add buttons below the grid

        // ✅ Scene setup
        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Opens the visualization window with selected parameters.
     */
    private void openSortingVisualization(String algorithm, String inputText, String speed) {
        SortingVisualizerApp.launchSortingVisualizer(algorithm, inputText, speed);
        primaryStage.close(); // Close settings window after launching visualization
    }

    public static void main(String[] args) {
        launch(args);
    }
}
