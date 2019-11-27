package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pesquisador extends Funcionario {

    private String areaAtuacao;
    private List<String> projetos;

    public Pesquisador(String nome, String endereco, String sexo, String dataNascimento, String salario, String departamento, String areaAtuacao) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.areaAtuacao = areaAtuacao;
        this.projetos = new ArrayList<String>();
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
