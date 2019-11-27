package daos.jpa;

import daos.FuncionarioDAO;
import models.Dependente;
import models.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FuncionarioRedisDAO extends GenericRedisDAO<Funcionario> implements FuncionarioDAO {

    public FuncionarioRedisDAO() {
        super("funcionarios");
    }

    @Override
    public void insert(Funcionario funcionario) {
        String userKey = "funcionarios:"+funcionario.getId()+":";
        redisClient.set(userKey+"id", String.valueOf(funcionario.getId()));
        save(funcionario, userKey);
    }

    @Override
    public void update(Funcionario funcionario) {
        // deletar coleções para que possam ser inseridas novamente
        String userKey = "funcionarios:"+funcionario.getId()+":";
        redisClient.del(userKey+"endereco");
        redisClient.del(userKey+"sexo");
        redisClient.del(userKey+"data_nascimento");
        redisClient.del(userKey+"salario");
        redisClient.del(userKey+"departamento");
        redisClient.del(userKey+"dependentes");
        save(funcionario, userKey);
    }

    @Override
    public Funcionario find(Object id) {
        String userKey = "funcionarios:"+id+":";
        if(redisClient.get(userKey+"id") == null) return null;
        return fromRedis((int)id, userKey);
    }

    @Override
    public List<Funcionario> findAll() {

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Set<String> keys = redisClient.keys("funcionarios:*:id");
        for(String key : keys) {
            int id = Integer.parseInt(redisClient.get(key));
            String userKey = "users:"+id+":";
            users.add(fromRedis(id, userKey));
        }
        return users;
    }

    private void save(Funcionario funcionario, String userKey) {

        redisClient.set(userKey+"nome", funcionario.getNome());

        redisClient.set(userKey+"endereco", funcionario.getEndereco());

        redisClient.set(userKey+"sexo", funcionario.getSexo());

        redisClient.set(userKey+"data_nascimento", funcionario.getDataNascimento());

        redisClient.set(userKey+"salario", String.valueOf(funcionario.getSalario()));

        redisClient.set(userKey+"departamento", String.valueOf(funcionario.getDepartamento()));

        for(Dependente dependente: funcionario.getDependentes()) {
            redisClient.rpush(userKey+"dependentes", String.valueOf(dependente));
        }

    }
}
