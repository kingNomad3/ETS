package com.example.labo4;

import javafx.application.Platform;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SortingAlgorithm {
    protected int[] array;
    protected SortVisualizer visualizer;
    protected int speed; // Vitesse de l'animation
    protected AtomicBoolean isPaused; // Controls pause state

    public SortingAlgorithm(int[] array, SortVisualizer visualizer, int speed, AtomicBoolean isPaused) {
        this.array = array;
        this.visualizer = visualizer;
        this.speed = speed;
        this.isPaused = isPaused;
    }

    // ✅ Template Method for Sorting
    public final void sort() {
        divideAndSort(0, array.length - 1);
        visualizer.drawArray();
    }

    // Abstract methods for QuickSort and MergeSort implementations
    protected abstract void divideAndSort(int low, int high);
    protected abstract void mergeOrPartition(int low, int mid, int high);

    // ✅ Update the visualization with pause functionality
    protected void updateVisualization() {
        synchronized (isPaused) {
            while (isPaused.get()) { // If paused, wait
                try {
                    isPaused.wait(); // Wait until notified
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        Platform.runLater(() -> visualizer.drawArray()); // Update the UI
        try {
            Thread.sleep(speed); // Apply animation speed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
