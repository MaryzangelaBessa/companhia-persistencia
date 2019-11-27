package daos.redis;

import daos.DepartamentoDAO;
import models.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DepartamentoRedisDAO extends GenericRedisDAO<Departamento> implements DepartamentoDAO {

    public DepartamentoRedisDAO() { super("departamentos"); }

    @Override
    public void insert(Departamento departamento) {
        String departKey = "departamentos:"+departamento.getNumero()+":";
        redisClient.set(departKey+"id", String.valueOf(departamento.getNumero()));
        save(departamento, departKey);
    }

    @Override
    public void update(Departamento departamento) {
        // deletar coleções para que possam ser inseridas novamente
        String departKey = "departamentos:"+departamento.getNumero()+":";
        redisClient.del(departKey+"funcionarios");
        redisClient.del(departKey+"projetos");
        save(departamento, departKey);
    }

    @Override
    public Departamento find(Object id) {
        String departKey = "departamentos:"+id+":";
        if(redisClient.get(departKey+"id") == null) return null;
        return fromRedis((Long) id, departKey);
    }

    @Override
    public List<Departamento> findAll() {

        List<Departamento> departamentos = new ArrayList<Departamento>();
        Set<String> keys = redisClient.keys("departamentos:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String departKey = "departamentos:"+id+":";
            departamentos.add(fromRedis(id, departKey));
        }
        return departamentos;
    }

    private Departamento fromRedis(Long id, String departKey) {

        String nome = redisClient.get(departKey+"nome");
        List<String> funcionarios = redisClient.lrange(departKey+"funcionarios", 0, -1);
        List<String> projetos = redisClient.lrange(departKey+"projetos", 0, -1);

        Departamento departamento = new Departamento();

        departamento.setNumero(id);
        departamento.setNome(nome);
        departamento.setFuncionarios(funcionarios);
        departamento.setProjetos(projetos);
        return departamento;
    }

    private void save(Departamento departamento, String departKey) {

        redisClient.set(departKey+"nome", departamento.getNome());

        for(String funcionario: departamento.getFuncionarios()) {
            redisClient.rpush(departKey+"funcionarios", funcionario);
        }

        for(String projeto: departamento.getProjetos()) {
            redisClient.rpush(departKey+"projetos", projeto);
        }

    }
}
