package daos.redis;

import daos.AuxLimpezaDAO;
import models.AuxLimpeza;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuxLimpezaRedisDAO extends GenericRedisDAO<AuxLimpeza> implements AuxLimpezaDAO {

    public AuxLimpezaRedisDAO() {
        super("auxilares_limpeza");
    }

    @Override
    public void insert(AuxLimpeza auxLimpeza) {
        String auxKey = "funcionarios:" + auxLimpeza.getId() + ":";
        redisClient.set(auxKey + "id", String.valueOf(auxLimpeza.getId()));
        save(auxLimpeza, auxKey);
    }

    @Override
    public void update(AuxLimpeza auxLimpeza) {
        String auxKey = "funcionarios:" + auxLimpeza.getId() + ":";
        redisClient.del(auxKey + "endereco");
        redisClient.del(auxKey + "sexo");
        redisClient.del(auxKey + "data_nascimento");
        redisClient.del(auxKey + "salario");
        redisClient.del(auxKey + "departamento");
        redisClient.del(auxKey + "cargo");
        redisClient.del(auxKey + "horas_joranda_trabalhada");
        redisClient.del(auxKey + "gerente");
        // redisClient.del(auxKey + "gerenciados");
        save(auxLimpeza, auxKey);
    }

    @Override
    public AuxLimpeza find(Object id) {
        String auxKey = "auxiliares_limpeza:" + id + ":";
        if (redisClient.get(auxKey + "id") == null) return null;
        return fromRedis((Long) id, auxKey);
    }

    @Override
    public List<AuxLimpeza> findAll() {
        List<AuxLimpeza> auxLimpezaList = new ArrayList<AuxLimpeza>();
        Set<String> keys = redisClient.keys("auxLimpezaList:*:id");

        for (String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String auxKey = "auxiliares_limpeza:" + id + ":";
            auxLimpezaList.add(fromRedis(id, auxKey));
        }

        return auxLimpezaList;
    }

    private AuxLimpeza fromRedis(Long id, String auxKey) {

        String nome = redisClient.get(auxKey + "nome");
        String endereco = redisClient.get(auxKey + "endereco");
        String sexo = redisClient.get(auxKey + "sexo");
        String data_nascimento = redisClient.get(auxKey + "data_nascimento");
        String salario = redisClient.get(auxKey + "salario");
        String departamento = redisClient.get(auxKey + "departamento");
        String cargo = redisClient.get(auxKey + "cargo");
        String horasJornadaTrabalhada = redisClient.get(auxKey + "horas_jornada_trabalhada");
        String gerente = redisClient.get(auxKey + "gerente");
        // List<String> gerenciados = redisClient.lrange(auxKey + "gerenciados", 0, -1);

        AuxLimpeza auxLimpeza = new AuxLimpeza();
        auxLimpeza.setId(id);
        auxLimpeza.setNome(nome);
        auxLimpeza.setEndereco(endereco);
        auxLimpeza.setSexo(sexo);
        auxLimpeza.setDataNascimento(data_nascimento);
        auxLimpeza.setSalario(Double.parseDouble(salario));
        auxLimpeza.setDepartamento(departamento);
        auxLimpeza.setCargo(cargo);
        auxLimpeza.setHorasJornadaTrabalho(Integer.parseInt(horasJornadaTrabalhada));
        auxLimpeza.setAuxLimpezaGerente(gerente);
        // auxLimpeza.getAuxLimpezaGerenciados();

        return auxLimpeza;
    }

    private void save(AuxLimpeza auxLimpeza, String auxKey) {

        redisClient.set(auxKey + "nome", auxLimpeza.getNome());
        redisClient.set(auxKey + "endereco", auxLimpeza.getEndereco());
        redisClient.set(auxKey + "sexo", auxLimpeza.getSexo());
        redisClient.set(auxKey + "data_nascimento", auxLimpeza.getDataNascimento());
        redisClient.set(auxKey + "salario", String.valueOf(auxLimpeza.getSalario()));
        redisClient.set(auxKey + "departamento", auxLimpeza.getDepartamento());
        redisClient.set(auxKey + "cargo", auxLimpeza.getCargo());
        redisClient.set(auxKey + "horas_jornada_trabalho", String.valueOf(auxLimpeza.getHorasJornadaTrabalho()));
        redisClient.set(auxKey + "gerente", String.valueOf(auxLimpeza.getAuxLimpezaGerente()));
        // redisClient.set(auxKey + "gerenciados", auxLimpeza.getAuxLimpezaGerenciados());

        /*
        for (String gerenciados : auxLimpeza.getAuxLimpezaGerenciados()) {
            redisClient.rpush(auxKey + "dependentes", gerenciados);
        }
         */
    }
}
