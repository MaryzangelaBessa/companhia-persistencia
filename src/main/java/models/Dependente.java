package models;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(name = "dependente")
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "data_nascimento")
    private Calendar dataNascimento;

    private Funcionario funcionario;

    public Dependente() {
    }

    public Dependente(String nome, String sexo, Calendar dataNascimento, Funcionario funcionario) {
        this(0, nome, sexo, dataNascimento, funcionario);
    }

    public Dependente(int id, String nome, String sexo, Calendar dataNascimento, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
