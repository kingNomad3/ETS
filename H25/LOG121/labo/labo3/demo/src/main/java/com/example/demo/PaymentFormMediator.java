package com.example.demo;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PaymentFormMediator {

    private ComboBox<PaymentOption> paymentMethod;
    private TextField creditCardNumber, expirationDate, cvv;
    private TextField giftCardNumber;
    private TextField shippingAddress, billingAdress;
    private RadioButton sameAddress;
    private ComboBox<DeliveryOption> deliveryOption;

    public PaymentFormMediator(ComboBox<PaymentOption> paymentMethod, TextField creditCardNumber,
                               TextField expirationDate, TextField cvv, TextField giftCardNumber, TextField shippingAddress,
                               TextField billingAdress, RadioButton sameAddress, ComboBox<DeliveryOption> deliveryOption) {
        this.paymentMethod = paymentMethod;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.giftCardNumber = giftCardNumber;
        this.shippingAddress = shippingAddress;
        this.billingAdress = billingAdress;
        this.sameAddress = sameAddress;
        this.deliveryOption = deliveryOption;

        setupListeners();
        updatePaymentFields();

    }

    private void setupListeners() {
        paymentMethod.setOnAction(actionEvent -> updatePaymentFields());
        sameAddress.setOnAction(actionEvent -> synchronizeAddresses());
        shippingAddress.textProperty().addListener((observable,oldValue,newValue) -> {
            if(sameAddress.isSelected()){
                billingAdress.setText(newValue);
            }
        });
    }

    private void updatePaymentFields() {
        PaymentOption  method = paymentMethod.getValue();
        boolean isCreditCard = (method == PaymentOption.CARTE_CREDIT);
        boolean isGiftCard = (method == PaymentOption.CARTE_CADEAU);
        boolean isCash = (method == PaymentOption.PAIEMENT_LIVRAISON);

        //activer ou desactiver les champs selon la methode de paiment
        creditCardNumber.setDisable(!isCreditCard);
        expirationDate.setDisable(!isCreditCard);
        cvv.setDisable(!isCreditCard);

        giftCardNumber.setDisable(!isGiftCard);

        //option de livraison
        deliveryOption.getItems().clear();
        if (isCash) {
            deliveryOption.setItems(FXCollections.observableArrayList(
                    DeliveryOption.MAIN_PROPRE, DeliveryOption.EXTERIEUR
            ));
        } else {
            deliveryOption.setItems(FXCollections.observableArrayList(
                    DeliveryOption.MAIN_PROPRE, DeliveryOption.EXTERIEUR, DeliveryOption.LAISSER_PORTE
            ));
        }
    }

    private void synchronizeAddresses(){
        if(sameAddress.isSelected()){
            billingAdress.setText(shippingAddress.getText());
            billingAdress.setDisable(true);
        }else{
            billingAdress.setDisable(false);
        }
    }



}
