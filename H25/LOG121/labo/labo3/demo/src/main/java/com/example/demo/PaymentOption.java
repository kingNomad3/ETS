package com.example.demo;

public enum PaymentOption {
    CARTE_CREDIT("Carte de crédit"),
    CARTE_CADEAU("Carte cadeau"),
    PAIEMENT_LIVRAISON("Paiement à la livraison");

    private final String label;

    PaymentOption(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

    @Override
    public String toString(){
        return label;
    }



}
