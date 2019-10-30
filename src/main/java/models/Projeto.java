package models;

import models.funcionarios.Pesquisador;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "horas_duracao")
    private int horasDuracao;

    @Column(name = "departamento")
    private Departamento departamento;

    @Column(name = "pesquisadores")
    private List<Pesquisador> pesquisadores;

    public Projeto() {
    }

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
                ", pesquisadores=" + pesquisadores +
                '}';
    }
}
