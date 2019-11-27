package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Funcionario {

    private Long id;
    private String nome;
    private String endereco;
    private String sexo;
    private String dataNascimento;
    private String salario;
    private String departamento;
    private List<String> dependentes;

    public Funcionario(String nome, String endereco, String sexo, String dataNascimento, String salario, String departamento) {
        this.id = null;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.departamento = departamento;
        this.dependentes = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return ">  (" + id +
                ") " + nome +
                "\n       Endereço: " + endereco +
                "\n       Sexo: " + sexo +
                "\n       Data de Nascimento: " + dataNascimento +
                "\n       Salário: " + salario;
    }
}