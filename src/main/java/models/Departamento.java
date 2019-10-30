package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "funcionarios")
    private List<Funcionario> funcionarios;

    @Column(name = "projetos")
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
