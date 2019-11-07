package models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
    private String dataNascimento;

    // @Column(name = "funcionario")
    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;

    public Dependente(String nome, String sexo, String dataNascimento, Funcionario funcionario) {
        this.id = null;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return ">  (" + id +
                ") " + nome +
                "\n       Sexo: " + sexo +
                "\n       Data de Nascimento: " + dataNascimento;
    }
}
