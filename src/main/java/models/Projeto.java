package models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "horas_duracao")
    private int horasDuracao;

    // @Column(name = "departamento")
    @ManyToOne
    @JoinColumn(name = "departamento_numero")
    private Departamento departamento;

    @Column(name = "pesquisadores")
    @ManyToMany(mappedBy = "projetos")
    private List<Pesquisador> pesquisadores;

    public Projeto(String nome, int horasDuracao, Departamento departamento) {
        this.nome = nome;
        this.horasDuracao = horasDuracao;
        this.departamento = departamento;
        this.pesquisadores = new ArrayList<Pesquisador>();
    }
}
