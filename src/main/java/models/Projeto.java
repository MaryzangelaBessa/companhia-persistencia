package models;

import models.funcionarios.Pesquisador;

import java.util.List;

public class Projeto {
    private int numero;
    private String nome;
    private int horasDuracao;
    private Departamento departamento;
    private List<Pesquisador> pesquisadores;

    public Projeto() {}

    public Projeto(String nome, int horasDuracao, Departamento departamento, List<Pesquisador> pesquisadores) {
        this.nome = nome;
        this.horasDuracao = horasDuracao;
        this.departamento = departamento;
        this.pesquisadores = pesquisadores;
    }

    public Projeto(int numero, String nome, int horasDuracao, Departamento departamento, List<Pesquisador> pesquisadores) {
        this.numero = numero;
        this.nome = nome;
        this.horasDuracao = horasDuracao;
        this.departamento = departamento;
        this.pesquisadores = pesquisadores;
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

    public int getHorasDuracao() {
        return horasDuracao;
    }

    public void setHorasDuracao(int horasDuracao) {
        this.horasDuracao = horasDuracao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", horasDuracao=" + horasDuracao +
                ", departamento=" + departamento +
                ", pesquisadores=" + pesquisadores +
                '}';
    }
}
