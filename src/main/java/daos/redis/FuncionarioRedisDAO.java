package daos.redis;

import daos.FuncionarioDAO;
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
        String funcKey = "funcionarios:"+funcionario.getId()+":";
        redisClient.set(funcKey+"id", String.valueOf(funcionario.getId()));
        save(funcionario, funcKey);
    }

    @Override
    public void update(Funcionario funcionario) {
        // deletar coleções para que possam ser inseridas novamente
        String funcKey = "funcionarios:"+funcionario.getId()+":";
        redisClient.del(funcKey+"endereco");
        redisClient.del(funcKey+"sexo");
        redisClient.del(funcKey+"data_nascimento");
        redisClient.del(funcKey+"salario");
        redisClient.del(funcKey+"departamento");
        redisClient.del(funcKey+"dependentes");
        save(funcionario, funcKey);
    }

    @Override
    public Funcionario find(Object id) {
        String funcKey = "funcionarios:"+id+":";
        if(redisClient.get(funcKey+"id") == null) return null;
        return fromRedis((Long) id, funcKey);
    }

    @Override
    public List<Funcionario> findAll() {

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Set<String> keys = redisClient.keys("funcionarios:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "funcionarios:"+id+":";
            funcionarios.add(fromRedis(id, funcKey));
        }
        return funcionarios;
    }

    private Funcionario fromRedis(Long id, String funcKey) {

        String nome = redisClient.get(funcKey+"nome");
        String endereco = redisClient.get(funcKey+"endereco");
        String sexo = redisClient.get(funcKey+"sexo");
        String data_nascimento = redisClient.get(funcKey+"data_nascimento");
        String salario = redisClient.get(funcKey+"salario");
        String departamento = redisClient.get(funcKey+"departamento");
        List<String> dependentes = redisClient.lrange(funcKey+"dependentes", 0, -1);

        Funcionario funcionario = new Funcionario();

        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setEndereco(endereco);
        funcionario.setSexo(sexo);
        funcionario.setDataNascimento(data_nascimento);
        funcionario.setSalario(salario);
        funcionario.setDepartamento(departamento);
        funcionario.setDependentes(dependentes);

        return funcionario;
    }

    private void save(Funcionario funcionario, String funcKey) {

        redisClient.set(funcKey+"nome", funcionario.getNome());

        redisClient.set(funcKey+"endereco", funcionario.getEndereco());

        redisClient.set(funcKey+"sexo", funcionario.getSexo());

        redisClient.set(funcKey+"data_nascimento", funcionario.getDataNascimento());

        redisClient.set(funcKey+"salario", String.valueOf(funcionario.getSalario()));

        redisClient.set(funcKey+"departamento", funcionario.getDepartamento());

        for(String dependente: funcionario.getDependentes()) {
            redisClient.rpush(funcKey+"dependentes", dependente);
        }

    }
}
