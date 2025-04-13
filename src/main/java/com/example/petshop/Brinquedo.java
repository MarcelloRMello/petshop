package com.example.petshop;

// Classe derivada Brinquedo
class Brinquedo extends Produto {
    private String material;

    public Brinquedo(String nome, double preco, String material) {
        super(nome, preco);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}