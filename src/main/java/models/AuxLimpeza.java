package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuxLimpeza extends Funcionario {

    private String cargo;
    private int horasJornadaTrabalho;
    private String auxLimpezaGerente;
    private List<AuxLimpeza> auxLimpezaGerenciados;

    public AuxLimpeza(String nome, String endereco, String sexo, String dataNascimento, Double salario, String departamento, String cargo, int horasJornadaTrabalho) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = null;
        this.auxLimpezaGerenciados = new ArrayList<AuxLimpeza>();
    }

    @Override
    public String toString() {
        return ">  (" + this.getId() +
                ") " + this.getNome() +
                "\n       Endereço: " + this.getEndereco() +
                "\n       Sexo: " + this.getSexo() +
                "\n       Data de Nascimento: " + this.getDataNascimento() +
                "\n       Salário: " + this.getSalario() +
                "\n       Cargo: " + cargo +
                "\n       Jornada de trabalho de " + horasJornadaTrabalho + " horas";
    }
}
