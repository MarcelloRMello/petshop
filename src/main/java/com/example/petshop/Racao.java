package com.example.petshop;

// Classe derivada Racao
class Racao extends Produto {
    private String tipo;

    public Racao(String nome, double preco, String tipo) {
        super(nome, preco);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}