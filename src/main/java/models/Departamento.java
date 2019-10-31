package models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "funcionarios")
    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;

    @Column(name = "projetos")
    @OneToMany(mappedBy = "departamento")
    private List<Projeto> projetos;

    public Departamento(String nome, List<Funcionario> funcionarios, List<Projeto> projetos) {
        this(0, nome, funcionarios, projetos);
    }
}
