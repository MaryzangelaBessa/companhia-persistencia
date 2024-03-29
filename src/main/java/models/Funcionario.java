package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private String dataNascimento;

    @Column(name = "salario")
    private Double salario;

    // @Column(name = "departamento")
    @ManyToOne
    @JoinColumn(name = "departamento_numero")
    private Departamento departamento;

    @Column(name = "dependentes")
    @OneToMany(mappedBy = "funcionario")
    private List<Dependente> dependentes;

    public Funcionario(String nome, String endereco, String sexo, String dataNascimento, Double salario, Departamento departamento) {
        this.id = null;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.departamento = departamento;
        this.dependentes = new ArrayList<Dependente>();
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