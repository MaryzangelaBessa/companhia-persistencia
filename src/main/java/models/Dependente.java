package models;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "funcionario")
    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;

    public Dependente(String nome, String sexo, Calendar dataNascimento, Funcionario funcionario) {
        this(0, nome, sexo, dataNascimento, funcionario);
    }
}
