package com.example.labo4;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SortVisualizer {
    private int[] array;
    private Pane displayPane;

    public SortVisualizer(int[] array, Pane displayPane) {
        this.array = array;
        this.displayPane = displayPane;
        drawArray();
    }

    // Draws the current state of the array as bars
    public void drawArray() {
        Platform.runLater(() -> {
            displayPane.getChildren().clear();
            double width = displayPane.getWidth() / array.length;
            double height = displayPane.getHeight();

            for (int i = 0; i < array.length; i++) {
                double barHeight = (array[i] / 100.0) * height; // Normalize height
                Rectangle rect = new Rectangle(i * width, height - barHeight, width - 2, barHeight);
                rect.setFill(Color.GRAY);
                displayPane.getChildren().add(rect);
            }
        });
    }

    // Highlights elements being compared/swapped
    public void highlight(int index1, int index2, Color color) {
        Platform.runLater(() -> {
            displayPane.getChildren().clear();
            double width = displayPane.getWidth() / array.length;
            double height = displayPane.getHeight();

            for (int i = 0; i < array.length; i++) {
                double barHeight = (array[i] / 100.0) * height;
                Rectangle rect = new Rectangle(i * width, height - barHeight, width - 2, barHeight);

                // Highlight selected elements
                if (i == index1 || i == index2) {
                    rect.setFill(color);
                } else {
                    rect.setFill(Color.GRAY);
                }

                displayPane.getChildren().add(rect);
            }
        });
    }
}
