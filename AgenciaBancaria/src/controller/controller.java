package controller;
import java.util.ArrayList;
import model.Pessoa;
import utils.Utils;
import model.Conta;
import javax.swing.*;


public class controller {
	public static void criarConta() {
        String nome = JOptionPane.showInputDialog("Nome do cliente:");
        String cpf = obterCPFFormatado(); // Chama uma função para obter CPF formatado
        String email = JOptionPane.showInputDialog("Email do cliente");

        if (cpf != null) {
            // Cria uma nova Pessoa com os detalhes do cliente
            Pessoa cliente = new Pessoa(nome, cpf, email);
            // Cria uma nova Conta associada a essa Pessoa
            Conta conta = new Conta(cliente);

            // Adiciona a conta à lista de contas
            

            JOptionPane.showMessageDialog(null, "Sua conta foi criada com sucesso!");
        }
    }

    public static String obterCPFFormatado() {
        while (true) {
            String cpf = JOptionPane.showInputDialog("CPF do cliente (formato: xxx.xxx.xxx-xx):");
            if (validarCPF(cpf)) {
                return cpf;
            }
            JOptionPane.showMessageDialog(null, "CPF inválido. O formato deve ser xxx.xxx.xxx-xx.");
        }
    }

    public static boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);

        if (primeiroDigito == 10 || primeiroDigito == 11) {
            primeiroDigito = 0;
        }

        if (primeiroDigito != cpf.charAt(9) - '0') {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);

        if (segundoDigito == 10 || segundoDigito == 11) {
            segundoDigito = 0;
        }

        return segundoDigito == cpf.charAt(10) - '0';
    }

    // As funções depositar(), sacar(), transferir(), listarContas() e gerarExtrato() seguem um padrão semelhante
    // Elas obtêm informações do usuário, como número da conta, valor, etc., e interagem com as contas.
    // Exibem mensagens de sucesso ou erro ao usuário usando o JOptionPane.

    public static void depositar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta:"));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja depositar?"));
            conta.depositar(valorDeposito);

            JOptionPane.showMessageDialog(null, "Seu depósito foi realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        }
    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta:"));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja sacar?"));

            if (conta.sacar(valorSaque)) {
                JOptionPane.showMessageDialog(null,"Não foi possível realizar o saque. Saldo insuficiente." );
            } else {
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        }
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog("Número da conta que vai enviar a transferência:"));
        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            int numeroContaDestinatario = Integer.parseInt(JOptionPane.showInputDialog("Número da conta do destinatário:"));
            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor da transferência:"));

                if (contaRemetente.transferencia(contaDestinatario, valor)) {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a transferência. Saldo insuficiente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conta do destinatário não encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta remetente não encontrada.");
        }
    }

    
}
