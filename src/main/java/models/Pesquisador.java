package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.Departamento;
import models.Dependente;
import models.Funcionario;
import models.Projeto;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString(callSuper = true)
@Table(name = "pesquisador")
public class Pesquisador extends Funcionario {

    @Column(name = "area_atuacao")
    private String areaAtuacao;

    @Column(name = "projetos")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "pesquisador_projeto",
            joinColumns = {@JoinColumn(name = "pesquisador_id")},
            inverseJoinColumns = {@JoinColumn(name = "projeto_id")}
    )
    private List<Projeto> projetos;

    public Pesquisador(String nome, String endereco, String sexo, String dataNascimento, Double salario, Departamento departamento, String areaAtuacao) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.areaAtuacao = areaAtuacao;
        this.projetos = new ArrayList<Projeto>();
    }

    @Override
    public String toString() {
        return ">  (" + this.getId() +
                ") " + this.getNome() +
                "\n       Endereço: " + this.getEndereco() +
                "\n       Sexo: " + this.getSexo() +
                "\n       Data de Nascimento: " + this.getDataNascimento() +
                "\n       Salário: " + this.getSalario() +
                "\n       Área de Atuação: " + areaAtuacao;
    }
}
