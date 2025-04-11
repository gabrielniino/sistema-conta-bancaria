package model;

import util.ContaException;
import util.Validador;

public class Conta {
    private final int numeroConta;
    private String titular;
    private double saldo;

    private static final double TAXA_SAQUE = 5.0;

    public Conta(int numeroConta, String titular) throws ContaException {
        Validador.validarTitular(titular);
        this.numeroConta = numeroConta;
        this.titular = titular;
    }

    public Conta(int numeroConta, String titular, double depositoInicial) throws ContaException {
        this(numeroConta, titular);
        depositar(depositoInicial);
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String novoNome) throws ContaException {
        Validador.validarTitular(novoNome);
        this.titular = novoNome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) throws ContaException {
        Validador.validarValorPositivo(valor);
        saldo += valor;
    }

    public void sacar(double valor) throws ContaException {
        Validador.validarValorPositivo(valor);
        if ((valor + TAXA_SAQUE) > saldo) {
            throw new ContaException("Saldo insuficiente para saque com taxa.");
        }
        saldo -= (valor + TAXA_SAQUE);
    }

    @Override
    public String toString() {
        return String.format("Conta %d, Titular: %s, Saldo: $ %.2f", numeroConta, titular, saldo);
    }
}
