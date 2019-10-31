package models.funcionarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.Departamento;
import models.Dependente;
import models.Funcionario;
import models.Projeto;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
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

    public Pesquisador(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String areaAtuacao, List<Projeto> projetos) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }

    public Pesquisador(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String areaAtuacao, List<Projeto> projetos) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }

    public Pesquisador(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String areaAtuacao, List<Projeto> projetos) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }

    public Pesquisador(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String areaAtuacao, List<Projeto> projetos) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }
}
