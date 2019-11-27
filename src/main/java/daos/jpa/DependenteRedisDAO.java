package daos.jpa;

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
        String funcKey = "dependentes:"+dependente.getId()+":";
        redisClient.set(funcKey+"id", String.valueOf(dependente.getId()));
        save(dependente, funcKey);
    }

    @Override
    public void update(Dependente dependente) {
        // deletar coleções para que possam ser inseridas novamente
        String funcKey = "dependentes:"+dependente.getId()+":";
        redisClient.del(funcKey+"sexo");
        redisClient.del(funcKey+"data_nascimento");
        redisClient.del(funcKey+"funcionario");
        save(dependente, funcKey);
    }

    @Override
    public Dependente find(Object id) {
        String funcKey = "dependentes:"+id+":";
        if(redisClient.get(funcKey+"id") == null) return null;
        return fromRedis((Long) id, funcKey);
    }

    @Override
    public List<Dependente> findAll() {

        List<Dependente> dependentes = new ArrayList<Dependente>();
        Set<String> keys = redisClient.keys("dependentes:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "dependentes:"+id+":";
            dependentes.add(fromRedis(id, funcKey));
        }
        return dependentes;
    }

    private Dependente fromRedis(Long id, String funcKey) {

        String nome = redisClient.get(funcKey+"nome");
        String sexo = redisClient.get(funcKey+"sexo");
        String data_nascimento = redisClient.get(funcKey+"data_nascimento");
        String funcionario = redisClient.get(funcKey+"funcionario");

        Dependente dependente = new Dependente();

        dependente.setId(id);
        dependente.setNome(nome);
        dependente.setSexo(sexo);
        dependente.setDataNascimento(data_nascimento);
        dependente.setFuncionario(funcionario);

        return dependente;
    }

    private void save(Dependente dependente, String funcKey) {

        redisClient.set(funcKey+"nome", dependente.getNome());

        redisClient.set(funcKey+"sexo", dependente.getSexo());

        redisClient.set(funcKey+"data_nascimento", dependente.getDataNascimento());

        redisClient.set(funcKey+"funcionario", dependente.getFuncionario());

    }
}
