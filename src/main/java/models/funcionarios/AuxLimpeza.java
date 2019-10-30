package models.funcionarios;

import models.Departamento;
import models.Dependente;
import models.Funcionario;

import java.util.Calendar;
import java.util.List;

public class AuxLimpeza extends Funcionario {
    private String cargo;
    private int horasJornadaTrabalho;
    private AuxLimpeza auxLimpezaGerente;
    private List<AuxLimpeza> auxLimpezaGerenciados;

    public AuxLimpeza() {}

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente) {
        super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(id, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getHorasJornadaTrabalho() {
        return horasJornadaTrabalho;
    }

    public void setHorasJornadaTrabalho(int horasJornadaTrabalho) {
        this.horasJornadaTrabalho = horasJornadaTrabalho;
    }

    public AuxLimpeza getAuxLimpezaGerente() {
        return auxLimpezaGerente;
    }

    public void setAuxLimpezaGerente(AuxLimpeza auxLimpezaGerente) {
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public List<AuxLimpeza> getAuxLimpezaGerenciados() {
        return auxLimpezaGerenciados;
    }

    public void setAuxLimpezaGerenciados(List<AuxLimpeza> auxLimpezaGerenciados) {
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
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
                ", cargo=" + cargo +
                ", jornada de trabalho=" + horasJornadaTrabalho + " horas" +
                ", gerente=" + auxLimpezaGerente +
                ", auxiliares de limpeza gerenciados=" + auxLimpezaGerenciados +
                '}';
    }
}
