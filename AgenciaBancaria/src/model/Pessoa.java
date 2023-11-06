package model;

import java.time.LocalDate;
import java.util.regex.Pattern;
import utils.Utils;

public class Pessoa {
    private static int counter = 1; // Variável estática para gerar números de cliente únicos

    private int numeroPessoa; // Número de cliente
    private String name; // Nome do cliente
    private String cpf; // CPF do cliente
    private String email; // E-mail do cliente
    private LocalDate accountCreationDate; // Data de criação da conta

    public Pessoa() {
    }

    public Pessoa(String name, String cpf, String email) {
        this.numeroPessoa = counter; // Atribui o próximo número de cliente disponível
        this.name = name; // Define o nome do cliente
        setCpf(cpf); // Chama um método para definir o CPF com validação
        setEmail(email); // Chama um método para definir o e-mail com validação
        this.accountCreationDate = LocalDate.now(); // Obtém a data atual
        counter++; // Incrementa o contador de clientes
    }

    public int getNumeroPessoa() {
        return numeroPessoa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        // Validação de CPF com expressão regular
        if (Pattern.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido. O formato deve ser xxx.xxx.xxx-xx.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Validação de e-mail com expressão regular
        if (Pattern.matches(".+@.+\\..+", email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public String toString() {
        return "\nName: " + getName() +
                "\nCPF: " + getCpf() +
                "\nEmail: " + getEmail() +
                "\nAccount Creation Date: " + Utils.dateToString(getAccountCreationDate());
    }
}
