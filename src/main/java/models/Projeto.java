package models;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    private Long numero;
    private String nome;
    private int horasDuracao;
    private String departamento;
    private List<String> pesquisadores;

    public Projeto(String nome, int horasDuracao, String departamento) {
        this.nome = nome;
        this.horasDuracao = horasDuracao;
        this.departamento = departamento;
        this.pesquisadores = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return ">  (" + numero +
                ") " + nome +
                "\n       " + horasDuracao + " horas de duração" +
                "\n       Departamento de " + departamento;
    }
}
