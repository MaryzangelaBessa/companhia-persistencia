package models;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "data_nascimento")
    private Calendar dataNascimento;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "departamento")
    private Departamento departamento;

    @Column(name = "dependentes")
    @OneToMany(mappedBy = "funcionario")
    private List<Dependente> dependentes;

    public Funcionario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento) {
        this(null, nome, endereco, sexo, dataNascimento, salario, departamento);
    }

    public Funcionario(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, List<Dependente> dependentes) {
        this(null, nome, endereco, sexo, dataNascimento, salario, departamento, dependentes);
    }

    public Funcionario(Long id, String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.departamento = departamento;
    }
}
