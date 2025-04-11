package app;

import model.Conta;
import service.ContaService;
import util.ContaException;
import util.Mensagens;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContaService service = new ContaService();

        try {
            int numero = service.lerNumeroConta(sc);
            String nome = service.lerNomeTitular(sc);

            Conta conta;
            try {
                if (service.desejaDepositoInicial(sc)) {
                    double valor = service.lerValorDeposito(sc);
                    conta = new Conta(numero, nome, valor);
                } else {
                    conta = new Conta(numero, nome);
                }
            } catch (ContaException e) {
                System.out.println("Erro ao criar conta: " + e.getMessage());
                return;
            }

            System.out.println("\n=== Dados da conta ===");
            System.out.println(conta);

            double deposito = service.lerValorDeposito(sc);
            conta.depositar(deposito);
            System.out.println(Mensagens.msgSucessoDeposito());
            System.out.println(conta);

            double saque = service.lerValorSaque(sc, conta);
            conta.sacar(saque);
            System.out.println(Mensagens.msgSucessoSaque());
            System.out.println(conta);

        } catch (Exception e) {
            System.out.println(Mensagens.msgErro("Erro inesperado: " + e.getMessage()));
        } finally {
            sc.close();
        }
    }
}
