package daos.jpa;

import daos.DepartamentoDAO;
import models.Departamento;
import models.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DepartamentoRedisDAO extends GenericRedisDAO<Departamento> implements DepartamentoDAO {

    public DepartamentoRedisDAO() { super("departamentos"); }

    @Override
    public void insert(Departamento departamento) {
        String funcKey = "departamentos:"+departamento.getNumero()+":";
        redisClient.set(funcKey+"id", String.valueOf(departamento.getNumero()));
        save(departamento, funcKey);
    }

    @Override
    public void update(Departamento departamento) {
        // deletar coleções para que possam ser inseridas novamente
        String funcKey = "departamentos:"+departamento.getNumero()+":";
        redisClient.del(funcKey+"funcionarios");
        redisClient.del(funcKey+"projetos");
        save(departamento, funcKey);
    }

    @Override
    public Departamento find(Object id) {
        String funcKey = "departamentos:"+id+":";
        if(redisClient.get(funcKey+"id") == null) return null;
        return fromRedis((Long) id, funcKey);
    }

    @Override
    public List<Departamento> findAll() {

        List<Departamento> departamentos = new ArrayList<Departamento>();
        Set<String> keys = redisClient.keys("departamentos:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "departamentos:"+id+":";
            departamentos.add(fromRedis(id, funcKey));
        }
        return departamentos;
    }

    private Departamento fromRedis(Long id, String funcKey) {

        String nome = redisClient.get(funcKey+"nome");
        List<String> funcionarios = redisClient.lrange(funcKey+"funcionarios", 0, -1);
        List<String> projetos = redisClient.lrange(funcKey+"projetos", 0, -1);

        Departamento departamento = new Departamento();

        departamento.setNumero(id);
        departamento.setNome(nome);
        departamento.setFuncionarios(funcionarios);
        departamento.setProjetos(projetos);
        return departamento;
    }

    private void save(Departamento departamento, String funcKey) {

        redisClient.set(funcKey+"nome", departamento.getNome());

        for(String funcionario: departamento.getFuncionarios()) {
            redisClient.rpush(funcKey+"funcionarios", funcionario);
        }

        for(String projeto: departamento.getProjetos()) {
            redisClient.rpush(funcKey+"projetos", projeto);
        }

    }
}
