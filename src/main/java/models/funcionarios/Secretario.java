package models.funcionarios;

import models.Departamento;
import models.Dependente;
import models.Funcionario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "secretario")
public class Secretario extends Funcionario {

    @Column(name = "grau_escolaridade")
    private String grauEscoladidade;

    public Secretario() {
    }

    public Secretario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String grauEscoladidade) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.grauEscoladidade = grauEscoladidade;
    }

    public Secretario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String grauEscoladidade) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.grauEscoladidade = grauEscoladidade;
    }

    public Secretario(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String grauEscoladidade) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.grauEscoladidade = grauEscoladidade;
    }

    public Secretario(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String grauEscoladidade) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.grauEscoladidade = grauEscoladidade;
    }

    public String getGrauEscoladidade() {
        return grauEscoladidade;
    }

    public void setGrauEscoladidade(String grauEscoladidade) {
        this.grauEscoladidade = grauEscoladidade;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + this.getId() +
                ", nome='" + this.getNome() + '\'' +
                ", endereco='" + this.getEndereco() + '\'' +
                ", sexo='" + this.getSexo() + '\'' +
                ", dataNascimento=" + this.getDataNascimento() +
                ", salario=" + this.getSalario() +
                ", dependentes=" + this.getDependentes() +
                ", grau de escolaridade=" + grauEscoladidade +
                '}';
    }
}
