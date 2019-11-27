package models;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    private Long numero;
    private String nome;
    private List<String> funcionarios;
    private List<String> projetos;

    public Departamento(String nome) {
        this.numero = null;
        this.nome = nome;
        this.funcionarios = new ArrayList<String>();
        this.projetos = new ArrayList<String>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamento that = (Departamento) o;

        return numero.equals(that.numero);
    }

    @Override
    public int hashCode() {
        return numero.hashCode();
    }

    @Override
    public String toString() {
        return ">  (" + numero +
                ") " + nome;
    }
}
