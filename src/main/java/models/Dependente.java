package models;

import java.util.Calendar;

public class Dependente {

    private int id;
    private String nome;
    private String sexo;
    private Calendar dataNascimento;
    private Funcionario funcionario;

    public Dependente() {}

    public Dependente(String nome, String sexo, Calendar dataNascimento, Funcionario funcionario) {
        this(0, nome, sexo, dataNascimento, funcionario);
    }

    public Dependente(int id, String nome, String sexo, Calendar dataNascimento, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
