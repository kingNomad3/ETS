package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        PaymentForm paymentForm = new PaymentForm();
        Scene scene = new Scene(paymentForm.getRoot(), 500, 400);

        primaryStage.setTitle("Paiement de la commande");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
