package com.example.demo;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Arrays;

public class PaymentForm {
    private VBox root;
    private ComboBox<PaymentOption> paymentMethod;
    private TextField creditCardNumber,expirationDate,cvv;
    private TextField giftCardNumber;
    private TextField shippingAddress, billingAddress;
    private RadioButton sameAddress;
    private ComboBox<DeliveryOption> deliveryOption;

    private final String TITRE = "Formulaire de paiment";


    public PaymentForm(){
        root = new VBox(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label(TITRE);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;-fx-underline: true;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //Mode de paiment
        paymentMethod = new ComboBox<>();
        paymentMethod.setItems(FXCollections.observableArrayList(PaymentOption.values()));
        paymentMethod.setValue(PaymentOption.CARTE_CREDIT);

        // Champs carte de crédit
        creditCardNumber = new TextField();
        expirationDate = new TextField();
        cvv = new TextField();

        // Champs carte cadeau
        giftCardNumber = new TextField();

        //addresse
        shippingAddress = new TextField();
        billingAddress = new TextField();
        sameAddress = new RadioButton("L'adresse de facturation est la meme que l'adresse de livraison");


        //options de livraison
        deliveryOption = new ComboBox<>();
        deliveryOption.setItems(FXCollections.observableArrayList(DeliveryOption.values()));
        deliveryOption.setValue(DeliveryOption.MAIN_PROPRE);


        // layout
        // Mode de paiement
        Label paymentLabel = new Label("Mode de paiement :");
        GridPane.setMargin(paymentLabel, new Insets(0, 0, 0,  0));
        grid.add(paymentLabel, 0, 0);
        grid.add(paymentMethod, 1, 0, 2, 1);

        // Numéro de carte de crédit
        Label cardNumberLabel = new Label("Numéro de carte de crédit :");
        GridPane.setMargin(cardNumberLabel, new Insets(0, 0, 0, 15));
        grid.add(cardNumberLabel, 0, 1);
        grid.add(creditCardNumber, 1, 1, 2, 1);

        // Date d'expiration
        Label expirationLabel = new Label("Date d'expiration :");
        GridPane.setMargin(expirationLabel, new Insets(0, 0, 0, 15));
        grid.add(expirationLabel, 0, 2);
        grid.add(expirationDate, 1, 2, 2, 1);

        // Code de sécurité
        Label cvvLabel = new Label("Code de sécurité :");
        GridPane.setMargin(cvvLabel, new Insets(0, 0, 0, 15));
        grid.add(cvvLabel, 0, 3);
        grid.add(cvv, 1, 3, 2, 1);

        // Numéro de carte cadeau
        Label giftCardLabel = new Label("Numéro de carte cadeau :");
        GridPane.setMargin(giftCardLabel, new Insets(0, 0, 0, 15));
        grid.add(giftCardLabel, 0, 4);
        grid.add(giftCardNumber, 1, 4, 2, 1);

        // Adresse de livraison
        Label shippingLabel = new Label("Adresse de livraison :");
        GridPane.setMargin(shippingLabel, new Insets(0, 0, 0, 0));
        grid.add(shippingLabel, 0, 5);
        grid.add(shippingAddress, 1, 5, 2, 1);

        // Checkbox (Adresse de facturation même que livraison)
        GridPane.setMargin(sameAddress, new Insets(5, 0, 0, 0));
        grid.add(sameAddress, 0, 6, 2, 1);

        // Adresse de facturation
        Label billingLabel = new Label("Adresse de facturation :");
        GridPane.setMargin(billingLabel, new Insets(0, 0, 0, 0));
        grid.add(billingLabel, 0, 7);
        grid.add(billingAddress, 1, 7, 2, 1);

        // Option de livraison
        Label deliveryLabel = new Label("Option de livraison :");
        GridPane.setMargin(deliveryLabel, new Insets(0, 0, 0, 0));
        grid.add(deliveryLabel, 0, 8);
        grid.add(deliveryOption, 1, 8, 2, 1);




        root.getChildren().addAll(titleLabel, grid);

        new PaymentFormMediator(paymentMethod, creditCardNumber, expirationDate, cvv, giftCardNumber,
                shippingAddress, billingAddress, sameAddress, deliveryOption);


    }

    public VBox getRoot(){
        return root;
    }
}
