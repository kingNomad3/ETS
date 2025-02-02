package com.example.demo;

public enum DeliveryOption {
    MAIN_PROPRE("Livraison en main propre"),
    EXTERIEUR("Se retrouver à l'exterieur"),
    LAISSER_PORTE("Laisser a la porte");

    private final String label;

    DeliveryOption(String label){
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
