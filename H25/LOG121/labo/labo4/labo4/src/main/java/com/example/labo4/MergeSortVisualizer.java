package com.example.labo4;

import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicBoolean;

public class MergeSortVisualizer extends SortingAlgorithm {
    public MergeSortVisualizer(int[] array, SortVisualizer visualizer, int speed, AtomicBoolean isPaused) {
        super(array, visualizer, speed, isPaused);
    }

    @Override
    protected void divideAndSort(int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2; // Trouver le milieu
            divideAndSort(low, mid);          // Trier la première moitié
            divideAndSort(mid + 1, high);     // Trier la seconde moitié
            mergeOrPartition(low, mid, high); // Fusionner les deux moitiés
        }
    }

    @Override
    protected void mergeOrPartition(int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        // Copier les éléments dans les sous-tableaux
        for (int i = 0; i < leftSize; i++) left[i] = array[low + i];
        for (int j = 0; j < rightSize; j++) right[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = low;

        // Fusion des deux sous-tableaux en ordre croissant
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k] = left[i++];
            } else {
                array[k] = right[j++];
            }
            visualizer.highlight(k, k, Color.PURPLE); // Met en surbrillance l'élément inséré
            updateVisualization();
            k++;
        }

        // Copier les éléments restants
        while (i < leftSize) {
            array[k] = left[i++];
            visualizer.highlight(k, k, Color.BLUE); // Marquer les éléments restants du sous-tableau gauche
            updateVisualization();
            k++;
        }

        while (j < rightSize) {
            array[k] = right[j++];
            visualizer.highlight(k, k, Color.GREEN); // Marquer les éléments restants du sous-tableau droit
            updateVisualization();
            k++;
        }
    }

    protected void updateVisualization() {
        Platform.runLater(() -> visualizer.drawArray());
        try {
            Thread.sleep(300); // Pause pour voir les étapes du tri
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}

