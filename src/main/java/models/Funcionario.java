package models;

import java.util.Calendar;
import java.util.List;

public class Funcionario {

    private int id;
    private String nome;
    private String endereco;
    private String sexo;
    private Calendar dataNascimento;
    private Double salario;
    private Departamento departamento;
    private List<Dependente> dependentes;

    public Funcionario() {}

    public Funcionario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento) {
        this(0, nome, endereco, sexo, dataNascimento, salario, departamento);
    }

    public Funcionario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes) {
        this(0, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
    }

    public Funcionario(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.departamento = departamento;
    }

    public Funcionario(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.departamento = departamento;
        this.dependentes = dependentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", salario=" + salario +
                ", dependentes=" + dependentes +
                '}';
    }
}
