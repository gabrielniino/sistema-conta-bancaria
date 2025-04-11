package service;

import model.Conta;
import util.ContaException;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaService {

    public int lerNumeroConta(Scanner sc) {
        while (true) {
            try {
                System.out.print("Informe o número da conta: ");
                int numero = sc.nextInt();
                Validador.validarNumeroConta(numero);
                sc.nextLine(); // limpar buffer
                return numero;
            } catch (ContaException | InputMismatchException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public String lerNomeTitular(Scanner sc) {
        while (true) {
            System.out.print("Informe o titular da conta: ");
            String nome = sc.nextLine();
            try {
                Validador.validarTitular(nome);
                return Validador.formatarNome(nome);
            } catch (ContaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public boolean desejaDepositoInicial(Scanner sc) {
        while (true) {
            System.out.print("Possui depósito inicial (s/n)? ");
            String entrada = sc.next().trim().toLowerCase();
            if (entrada.equals("s")) return true;
            if (entrada.equals("n")) return false;
            System.out.println("Erro: Responda apenas com 's' para sim ou 'n' para não.");
        }
    }

    public double lerValorDeposito(Scanner sc) {
        while (true) {
            try {
                System.out.print("Informe o valor do depósito: ");
                double valor = sc.nextDouble();
                Validador.validarValorPositivo(valor);
                Validador.validarDepositoMaximo(valor);
                return valor;
            } catch (ContaException | InputMismatchException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public double lerValorSaque(Scanner sc, Conta conta) {
        while (true) {
            try {
                System.out.print("Informe o valor do saque: ");
                double valor = sc.nextDouble();
                Validador.validarSaque(valor, conta.getSaldo());
                return valor;
            } catch (ContaException | InputMismatchException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}
