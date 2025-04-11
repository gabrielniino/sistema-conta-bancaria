package util;

public class Validador {

    public static void validarTitular(String nome) throws ContaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ContaException("O nome do titular não pode estar vazio.");
        }

        String nomeTrimado = nome.trim();

        if (!nomeTrimado.matches("[\\p{L}\\s]+")) {
            throw new ContaException("O nome do titular deve conter apenas letras e espaços.");
        }
    }

    public static void validarValorPositivo(double valor) throws ContaException {
        if (valor < 0.01) {
            throw new ContaException("O valor deve ser maior ou igual a 0.01.");
        }
    }

    public static void validarNumeroConta(int numero) throws ContaException {
        if (numero <= 0) {
            throw new ContaException("O número da conta deve ser um inteiro positivo.");
        }
    }

    public static void validarSaque(double valor, double saldo) throws ContaException {
        if (valor < 0.01) {
            throw new ContaException("O valor do saque deve ser maior ou igual a 0.01.");
        }
        if (valor + 5.0 > saldo) {
            throw new ContaException("Saldo insuficiente para realizar o saque (incluindo taxa de $5.00).");
        }
    }

    public static String formatarNome(String nome) {
        return nome.trim().replaceAll("\\s+", " ").toUpperCase(); // ou capitalize, se preferir
    }

    public static void validarDepositoMaximo(double valor) throws ContaException {
        if (valor > 1_000_000) {
            throw new ContaException("Depósito ultrapassa o limite permitido por transação.");
        }
    }
}
