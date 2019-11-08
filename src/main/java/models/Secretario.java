package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.Departamento;
import models.Dependente;
import models.Funcionario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString(callSuper = true)
@Table(name = "secretario")
public class Secretario extends Funcionario {

    @Column(name = "grau_escolaridade")
    private String grauEscoladidade;
    
    public Secretario(String nome, String endereco, String sexo, String dataNascimento, Double salario, Departamento departamento, String grauEscoladidade) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.grauEscoladidade = grauEscoladidade;
    }

    @Override
    public String toString() {
        return ">  (" + this.getId() +
                ") " + this.getNome() +
                "\n       Endereço: " + this.getEndereco() +
                "\n       Sexo: " + this.getSexo() +
                "\n       Data de Nascimento: " + this.getDataNascimento() +
                "\n       Salário: " + this.getSalario() +
                "\n       Grau de Escolaridade: " + grauEscoladidade;
    }
}
