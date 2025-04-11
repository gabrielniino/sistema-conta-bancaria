package model;

public class Conta {
    private final int numeroConta;
    private String titular;
    private double saldo;

    private static final double TAXA_SAQUE = 5.0;

    public Conta(int numeroConta, String titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
    }

    public Conta(int numeroConta, String titular, double depositoInicial) {
        this(numeroConta, titular);
        depositar(depositoInicial);
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String novoNome) {
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            this.titular = novoNome;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor + TAXA_SAQUE <= saldo) {
            saldo -= (valor + TAXA_SAQUE);
        }
    }

    @Override
    public String toString() {
        return String.format("Conta %d, Titular: %s, Saldo: $ %.2f", numeroConta, titular, saldo);
    }
}
