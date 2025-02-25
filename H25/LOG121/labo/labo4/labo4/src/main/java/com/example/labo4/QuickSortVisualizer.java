package com.example.labo4;

import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicBoolean;

public class QuickSortVisualizer extends SortingAlgorithm {

    public QuickSortVisualizer(int[] array, SortVisualizer visualizer, int speed, AtomicBoolean isPaused) {
        super(array, visualizer, speed, isPaused);
    }

    @Override
    protected void divideAndSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            divideAndSort(low, pivotIndex - 1);
            divideAndSort(pivotIndex + 1, high);
        }
    }

    @Override
    protected void mergeOrPartition(int low, int mid, int high) {
        // QuickSort ne fusionne pas, donc cette méthode est vide
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j);
                visualizer.highlight(i, j, Color.PURPLE);
                updateVisualization(); // Correction de la faute de frappe
            }
        }
        swap(i + 1, high);
        visualizer.highlight(i + 1, high, Color.RED);
        updateVisualization(); // Correction de la faute de frappe

        return i + 1;
    }

    protected void updateVisualization() {
        Platform.runLater(() -> visualizer.drawArray()); // Met à jour l'affichage des barres
        try {
            Thread.sleep(300); // Pause pour voir les étapes du tri
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
