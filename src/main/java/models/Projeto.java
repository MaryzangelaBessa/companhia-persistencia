package models;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    private Long numero;
    private String nome;
    private int horasDuracao;
    private Departamento departamento;
    private List<Pesquisador> pesquisadores;

    public Projeto(String nome, int horasDuracao, Departamento departamento) {
        this.nome = nome;
        this.horasDuracao = horasDuracao;
        this.departamento = departamento;
        this.pesquisadores = new ArrayList<Pesquisador>();
    }

    @Override
    public String toString() {
        return ">  (" + numero +
                ") " + nome +
                "\n       " + horasDuracao + " horas de duração" +
                "\n       Departamento de " + departamento.getNome();
    }
}
