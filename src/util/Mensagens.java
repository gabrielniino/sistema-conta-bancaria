package util;

public class Mensagens {

    public static String msgSucessoDeposito() {
        return "Depósito realizado com sucesso!";
    }

    public static String msgSucessoSaque() {
        return "Saque realizado com sucesso! (taxa de $5.00 aplicada)";
    }

    public static String msgErro(String erro) {
        return "Erro: " + erro;
    }
}
