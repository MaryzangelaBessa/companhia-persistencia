package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trabalha {

    private Long id;
    private String pesquisador;
    private String projeto;
    private int horasSemanaisTrabalhadas;
}
