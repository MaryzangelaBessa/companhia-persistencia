package daos.jpa;

import daos.SecretarioDAO;
import models.Secretario;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SecretarioRedisDAO extends GenericRedisDAO<Secretario> implements SecretarioDAO {

    public SecretarioRedisDAO() {
        super("secretarios");
    }

    @Override
    public void insert(Secretario secretario) {
        String funcKey = "secretarios:"+secretario.getId()+":";
        redisClient.set(funcKey+"id", String.valueOf(secretario.getId()));
        save(secretario, funcKey);
    }

    @Override
    public void update(Secretario secretario) {
        // deletar coleções para que possam ser inseridas novamente
        String funcKey = "secretarios:"+secretario.getId()+":";
        redisClient.del(funcKey+"endereco");
        redisClient.del(funcKey+"sexo");
        redisClient.del(funcKey+"data_nascimento");
        redisClient.del(funcKey+"salario");
        redisClient.del(funcKey+"departamento");
        redisClient.del(funcKey+"dependentes");
        redisClient.del(funcKey+"grau_escolaridade");
        save(secretario, funcKey);
    }

    @Override
    public Secretario find(Object id) {
        String funcKey = "secretarios:"+id+":";
        if(redisClient.get(funcKey+"id") == null) return null;
        return fromRedis((Long) id, funcKey);
    }

    @Override
    public List<Secretario> findAll() {

        List<Secretario> secretarios = new ArrayList<Secretario>();
        Set<String> keys = redisClient.keys("secretarios:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "secretarios:"+id+":";
            secretarios.add(fromRedis(id, funcKey));
        }
        return secretarios;
    }

    private Secretario fromRedis(Long id, String funcKey) {

        String nome = redisClient.get(funcKey+"nome");
        String endereco = redisClient.get(funcKey+"endereco");
        String sexo = redisClient.get(funcKey+"sexo");
        String data_nascimento = redisClient.get(funcKey+"data_nascimento");
        String salario = redisClient.get(funcKey+"salario");
        String departamento = redisClient.get(funcKey+"departamento");
        List<String> dependentes = redisClient.lrange(funcKey+"dependentes", 0, -1);
        String grau_escolaridade = redisClient.get(funcKey+"grau_escolaridade");

        Secretario secretario = new Secretario();

        secretario.setId(id);
        secretario.setNome(nome);
        secretario.setEndereco(endereco);
        secretario.setSexo(sexo);
        secretario.setDataNascimento(data_nascimento);
        secretario.setSalario(Double.parseDouble(salario));
        secretario.setDepartamento(departamento);
        secretario.setDependentes(dependentes);
        secretario.setGrauEscoladidade(grau_escolaridade);

        return secretario;
    }

    private void save(Secretario secretario, String funcKey) {

        redisClient.set(funcKey+"nome", secretario.getNome());

        redisClient.set(funcKey+"endereco", secretario.getEndereco());

        redisClient.set(funcKey+"sexo", secretario.getSexo());

        redisClient.set(funcKey+"data_nascimento", secretario.getDataNascimento());

        redisClient.set(funcKey+"salario", String.valueOf(secretario.getSalario()));

        redisClient.set(funcKey+"departamento", secretario.getDepartamento());

        for(String dependente: secretario.getDependentes()) {
            redisClient.rpush(funcKey+"dependentes", dependente);
        }

        redisClient.set(funcKey+"grau_escolaridade", secretario.getGrauEscoladidade());
    }
}
