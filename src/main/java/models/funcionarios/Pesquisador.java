package models.funcionarios;

import models.Departamento;
import models.Dependente;
import models.Funcionario;
import models.Projeto;
import java.util.Calendar;
import java.util.List;

public class Pesquisador extends Funcionario {
    private String areaAtuacao;
    private List<Projeto> projetos;

    public Pesquisador() {}

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
        super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }

    public Pesquisador(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String areaAtuacao, List<Projeto> projetos) {
        super(id, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.areaAtuacao = areaAtuacao;
        this.projetos = projetos;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
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
                ", área de atuação=" + areaAtuacao +
                '}';
    }
}
