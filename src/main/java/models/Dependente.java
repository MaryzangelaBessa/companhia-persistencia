package models;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "dependente")
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Long id;

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
        this.id = null;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }
}
