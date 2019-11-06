package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "auxiliar_limpeza")
public class AuxLimpeza extends Funcionario {

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "horas_jornada_trabalho")
    private int horasJornadaTrabalho;

    // @Column(name = "auxiliar_limpeza_gerente")
    @ManyToOne
    @JoinColumn(name = "auxiliar_limpeza_gerente_id")
    private AuxLimpeza auxLimpezaGerente;

    @Column(name = "auxiliar_limpeza_gerenciados")
    @OneToMany(mappedBy = "auxLimpezaGerente")
    private List<AuxLimpeza> auxLimpezaGerenciados;

    public AuxLimpeza(String nome, String endereco, String sexo, Calendar dataNascimento, Double salario, Departamento departamento, String cargo, int horasJornadaTrabalho) {
        super(nome, endereco, sexo, dataNascimento, salario, departamento);
        this.cargo = cargo;
        this.horasJornadaTrabalho = horasJornadaTrabalho;
        this.auxLimpezaGerente = null;
        this.auxLimpezaGerenciados = new ArrayList<AuxLimpeza>();
    }

}
