package daos.redis;

import daos.DependenteDAO;
import models.Dependente;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DependenteRedisDAO extends GenericRedisDAO<Dependente> implements DependenteDAO {

    public DependenteRedisDAO() {
        super("dependentes");
    }

    @Override
    public void insert(Dependente dependente) {
        String depenKey = "dependentes:"+dependente.getId()+":";
        redisClient.set(depenKey+"id", String.valueOf(dependente.getId()));
        save(dependente, depenKey);
    }

    @Override
    public void update(Dependente dependente) {
        // deletar coleções para que possam ser inseridas novamente
        String depenKey = "dependentes:"+dependente.getId()+":";
        redisClient.del(depenKey+"sexo");
        redisClient.del(depenKey+"data_nascimento");
        redisClient.del(depenKey+"funcionario");
        save(dependente, depenKey);
    }

    @Override
    public Dependente find(Object id) {
        String depenKey = "dependentes:"+id+":";
        if(redisClient.get(depenKey+"id") == null) return null;
        return fromRedis((Long) id, depenKey);
    }

    @Override
    public List<Dependente> findAll() {

        List<Dependente> dependentes = new ArrayList<Dependente>();
        Set<String> keys = redisClient.keys("dependentes:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String depenKey = "dependentes:"+id+":";
            dependentes.add(fromRedis(id, depenKey));
        }
        return dependentes;
    }

    private Dependente fromRedis(Long id, String depenKey) {

        String nome = redisClient.get(depenKey+"nome");
        String sexo = redisClient.get(depenKey+"sexo");
        String data_nascimento = redisClient.get(depenKey+"data_nascimento");
        String funcionario = redisClient.get(depenKey+"funcionario");

        Dependente dependente = new Dependente();

        dependente.setId(id);
        dependente.setNome(nome);
        dependente.setSexo(sexo);
        dependente.setDataNascimento(data_nascimento);
        dependente.setFuncionario(funcionario);

        return dependente;
    }

    private void save(Dependente dependente, String depenKey) {

        redisClient.set(depenKey+"nome", dependente.getNome());

        redisClient.set(depenKey+"sexo", dependente.getSexo());

        redisClient.set(depenKey+"data_nascimento", dependente.getDataNascimento());

        redisClient.set(depenKey+"funcionario", dependente.getFuncionario());

    }
}
