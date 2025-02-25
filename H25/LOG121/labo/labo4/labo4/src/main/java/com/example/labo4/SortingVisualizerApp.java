package com.example.labo4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SortingVisualizerApp extends Application {
    private int[] array;
    private Pane displayPane;
    private SortingAlgorithm sortingAlgorithm;
    private SortVisualizer visualizer;
    private int speed; // Vitesse de l'animation
    private AtomicBoolean isPaused = new AtomicBoolean(false); // Controls pause state

    private String algorithmChoice;
    private String inputNumbers;
    private String speedChoice;

    public SortingVisualizerApp(String algorithm, String inputText, String speed) {
        this.algorithmChoice = algorithm;
        this.inputNumbers = inputText;
        this.speedChoice = speed;

        // Définir la vitesse d'animation (en millisecondes)
        switch (speed) {
            case "Lent": this.speed = 800; break;
            case "Normal": this.speed = 300; break;
            case "Rapide": this.speed = 100; break;
            default: this.speed = 300;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laboratoire 4 (LOG121) Benjamin");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        // Zone d'affichage des barres
        displayPane = new Pane();
        displayPane.setPrefSize(600, 300);
        displayPane.setStyle("-fx-background-color: white;");

        // ✅ Buttons: "Arrêt" (Pause) and "Démarrer" (Resume)
        Button stopButton = new Button("Arrêt");
        Button startButton = new Button("Démarrer");

        stopButton.setOnAction(e -> isPaused.set(true));  // Pause sorting
        startButton.setOnAction(e -> {
            synchronized (this) {  // Correctly synchronizing on "this" object
                isPaused.set(false);
                this.notify(); // Correctly notifying the paused thread
            }
        });

        HBox controls = new HBox(15, startButton, stopButton);
        controls.setAlignment(Pos.CENTER);

        root.getChildren().addAll(displayPane, controls);

        generateArray(); // Créer les nombres et dessiner la première vue

        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();

        new Thread(this::startSorting).start(); // Lancer le tri dans un thread séparé
    }

    /**
     * Génère un tableau de nombres à partir de l'entrée utilisateur.
     */
    private void generateArray() {
        array = Arrays.stream(inputNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        visualizer = new SortVisualizer(array, displayPane);
        visualizer.drawArray();
    }

    /**
     * Démarre le tri avec l'algorithme choisi.
     */
    private void startSorting() {
        if ("QuickSort".equals(algorithmChoice)) {
            sortingAlgorithm = new QuickSortVisualizer(array, visualizer, speed, isPaused);
        } else {
            sortingAlgorithm = new MergeSortVisualizer(array, visualizer, speed, isPaused);
        }
        sortingAlgorithm.sort(); // Exécuter le tri
    }

    /**
     * Lance la visualisation en créant une nouvelle fenêtre.
     */
    public static void launchSortingVisualizer(String algorithm, String inputText, String speed) {
        Platform.runLater(() -> {
            SortingVisualizerApp sortingApp = new SortingVisualizerApp(algorithm, inputText, speed);
            Stage stage = new Stage();
            sortingApp.start(stage);
        });
    }
}
