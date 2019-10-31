package models.funcionarios;

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
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "auxiliar_limpeza")
public class AuxLimpeza extends Funcionario {

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "horas_jornada_trabalho")
    private int horasJornadaTrabalho;

    @Column(name = "auxiliar_limpeza_gerente")
    private AuxLimpeza auxLimpezaGerente;

    @Column(name = "auxiliar_limpeza_gerenciados")
    private List<AuxLimpeza> auxLimpezaGerenciados;

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
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

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, List<AuxLimpeza> auxLimpezaGerenciados) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }

    public AuxLimpeza(int id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes, String cargo, int horasJornadaTrabalho, AuxLimpeza auxLimpezaGerente, List<AuxLimpeza> auxLimpezaGerenciados) {
        super((long) id, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = auxLimpezaGerente;
        this.auxLimpezaGerenciados = auxLimpezaGerenciados;
    }
}
