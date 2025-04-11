package app;

import model.Conta;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o número da conta: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Informe o titular da conta: ");
        String nome = sc.nextLine();

        System.out.print("Possui depósito inicial (s/n)? ");
        char temDeposito = sc.next().toLowerCase().charAt(0);

        Conta conta;

        if (temDeposito == 's') {
            System.out.print("Informe o valor do depósito inicial: ");
            double depositoInicial = sc.nextDouble();
            conta = new Conta(numero, nome, depositoInicial);
        } else {
            conta = new Conta(numero, nome);
        }

        System.out.println("\n=== Dados da conta ===");
        System.out.println(conta);

        System.out.print("\nInforme o valor para depósito: ");
        double deposito = sc.nextDouble();
        conta.depositar(deposito);

        System.out.println("Dados da conta atualizados:");
        System.out.println(conta);

        System.out.print("\nInforme o valor para saque: ");
        double saque = sc.nextDouble();
        conta.sacar(saque);

        System.out.println("Dados da conta atualizados:");
        System.out.println(conta);
        System.out.println("*Nota: O saque possui uma taxa fixa de $5.00.");

        sc.close();
    }
}
