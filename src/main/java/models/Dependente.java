package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Dependente {

    private Long id;
    private String nome;
    private String sexo;
    private String dataNascimento;
    private Funcionario funcionario;

    public Dependente(String nome, String sexo, String dataNascimento, Funcionario funcionario) {
        this.id = null;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return ">  (" + id +
                ") " + nome +
                "\n       Sexo: " + sexo +
                "\n       Data de Nascimento: " + dataNascimento;
    }
}
