package models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Long numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "funcionarios")
    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;

    @Column(name = "projetos")
    @OneToMany(mappedBy = "departamento")
    private List<Projeto> projetos;

    public Departamento(String nome) {
        this.numero = null;
        this.nome = nome;
        this.funcionarios = new ArrayList<Funcionario>();
        this.projetos = new ArrayList<Projeto>();
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
