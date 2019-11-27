package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Secretario extends Funcionario {

    private String grauEscoladidade;
    
    public Secretario(String nome, String endereco, String sexo, String dataNascimento, Double salario, String departamento, String grauEscoladidade) {
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
