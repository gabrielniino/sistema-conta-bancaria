package app;

import model.Conta;
import util.ContaException;
import util.Mensagens;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Conta conta = null;

        try {
            int numero;
            while (true) {
                try {
                    System.out.print("Informe o número da conta: ");
                    numero = sc.nextInt();
                    Validador.validarNumeroConta(numero);
                    sc.nextLine(); // limpar buffer
                    break;
                } catch (ContaException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Número inválido. Digite um número inteiro.");
                    sc.nextLine(); // limpar buffer
                }
            }

            String nome = "";
            while (true) {
                System.out.print("Informe o titular da conta: ");
                nome = sc.nextLine();
                try {
                    Validador.validarTitular(nome);
                    nome = Validador.formatarNome(nome); // Sanitização
                    break;
                } catch (ContaException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            char resposta;
            while (true) {
                System.out.print("Possui depósito inicial (s/n)? ");
                String entrada = sc.next().trim().toLowerCase();
                if (entrada.equals("s") || entrada.equals("n")) {
                    resposta = entrada.charAt(0);
                    break;
                } else {
                    System.out.println("Erro: Responda apenas com 's' para sim ou 'n' para não.");
                }
            }

            if (resposta == 's') {
                double depositoInicial;
                while (true) {
                    try {
                        System.out.print("Informe o valor do depósito inicial: ");
                        depositoInicial = sc.nextDouble();
                        Validador.validarValorPositivo(depositoInicial);
                        Validador.validarDepositoMaximo(depositoInicial);
                        break;
                    } catch (ContaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Valor inválido. Digite um número.");
                        sc.nextLine();
                    }
                }
                conta = new Conta(numero, nome, depositoInicial);
            } else {
                conta = new Conta(numero, nome);
            }

            System.out.println("\n=== Dados da conta ===");
            System.out.println(conta);

            double valorDeposito;
            while (true) {
                try {
                    System.out.print("\nInforme o valor para depósito: ");
                    valorDeposito = sc.nextDouble();
                    Validador.validarValorPositivo(valorDeposito);
                    Validador.validarDepositoMaximo(valorDeposito);
                    break;
                } catch (ContaException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Valor inválido. Digite um número.");
                    sc.nextLine();
                }
            }
            conta.depositar(valorDeposito);
            System.out.println(Mensagens.msgSucessoDeposito());
            System.out.println(conta);

            double valorSaque;
            while (true) {
                try {
                    System.out.print("\nInforme o valor para saque: ");
                    valorSaque = sc.nextDouble();
                    Validador.validarSaque(valorSaque, conta.getSaldo());
                    break;
                } catch (ContaException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Valor inválido. Digite um número.");
                    sc.nextLine();
                }
            }
            conta.sacar(valorSaque);
            System.out.println(Mensagens.msgSucessoSaque());
            System.out.println(conta);

        } catch (ContaException e) {
            System.out.println(Mensagens.msgErro(e.getMessage()));
        } catch (Exception e) {
            System.out.println(Mensagens.msgErro("Erro inesperado: " + e.getMessage()));
        } finally {
            sc.close();
        }
    }
}
