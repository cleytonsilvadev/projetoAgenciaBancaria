package model;

import javax.swing.JOptionPane;

import utils.Utils;

public class Conta {
    private static int accountCounter = 1; // Variável estática para gerar números de conta únicos
    private int numeroConta; // Número da conta
    private Pessoa pessoa; // Cliente associado à conta
    private Double saldo = 0.0; // Saldo inicial da conta

    public Conta(Pessoa pessoa) {
        this.numeroConta = Conta.accountCounter; // Atribui o próximo número de conta disponível
        this.pessoa = pessoa; // Associa a conta a um cliente
        this.updateSaldo(); // Atualiza o saldo da conta
        Conta.accountCounter += 1; // Incrementa o contador de contas
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Pessoa getClient() {
        return pessoa;
    }

    public void setClient(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    private void updateSaldo() {
        this.saldo = this.getSaldo(); // Atualiza o saldo (embora essa linha seja redundante)
    }

    public String toString() {
        return "\nBank account: " + this.getNumeroConta() +
                "\nCliente: " + this.pessoa.getName() +
                "\nCPF: " + this.pessoa.getCpf() +
                "\nEmail: " + this.pessoa.getEmail() +
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n";
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor); // Adiciona o valor ao saldo
            System.out.println("Seu depósito foi realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o depósito. O valor deve ser positivo.");
        }
    }

    public boolean sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor); // Subtrai o valor do saldo
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o saque. Verifique o valor e o saldo disponível.");
            return false; // Retorna false se o saque não foi bem-sucedido
        }
        return true; // Retorna true se o saque foi bem-sucedido
    }

    public boolean transferencia(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor); // Subtrai o valor do saldo da conta remetente
            contaParaDeposito.setSaldo(contaParaDeposito.getSaldo() + valor); // Adiciona o valor ao saldo da conta de destino
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a transferência. Verifique o valor e o saldo disponível.");
            return false; // Retorna false se a transferência não foi bem-sucedida
        }
        return true; // Retorna true se a transferência foi bem-sucedida
    }

    
}
