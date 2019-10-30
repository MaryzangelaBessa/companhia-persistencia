package models;

import java.util.List;

public class Departamento {
    private int numero;
    private String nome;
    private List<Funcionario> funcionarios;
    private List<Projeto> projetos;

    public Departamento() {}

    public Departamento(String nome, List<Funcionario> funcionarios, List<Projeto> projetos) {
        this(0, nome, funcionarios, projetos);
    }

    public Departamento(int numero, String nome, List<Funcionario> funcionarios, List<Projeto> projetos) {
        this.numero = numero;
        this.nome = nome;
        this.funcionarios = funcionarios;
        this.projetos = projetos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", funcionarios=" + funcionarios +
                ", projetos=" + projetos +
                '}';
    }
}
