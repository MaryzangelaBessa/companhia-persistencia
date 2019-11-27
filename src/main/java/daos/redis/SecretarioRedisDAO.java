package daos.redis;

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
        String secKey = "secretarios:"+secretario.getId()+":";
        redisClient.set(secKey+"id", String.valueOf(secretario.getId()));
        save(secretario, secKey);
    }

    @Override
    public void update(Secretario secretario) {
        // deletar coleções para que possam ser inseridas novamente
        String secKey = "secretarios:"+secretario.getId()+":";
        redisClient.del(secKey+"endereco");
        redisClient.del(secKey+"sexo");
        redisClient.del(secKey+"data_nascimento");
        redisClient.del(secKey+"salario");
        redisClient.del(secKey+"departamento");
        redisClient.del(secKey+"dependentes");
        redisClient.del(secKey+"grau_escolaridade");
        save(secretario, secKey);
    }

    @Override
    public Secretario find(Object id) {
        String secKey = "secretarios:"+id+":";
        if(redisClient.get(secKey+"id") == null) return null;
        return fromRedis((Long) id, secKey);
    }

    @Override
    public List<Secretario> findAll() {

        List<Secretario> secretarios = new ArrayList<Secretario>();
        Set<String> keys = redisClient.keys("secretarios:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String secKey = "secretarios:"+id+":";
            secretarios.add(fromRedis(id, secKey));
        }
        return secretarios;
    }

    private Secretario fromRedis(Long id, String secKey) {

        String nome = redisClient.get(secKey+"nome");
        String endereco = redisClient.get(secKey+"endereco");
        String sexo = redisClient.get(secKey+"sexo");
        String data_nascimento = redisClient.get(secKey+"data_nascimento");
        String salario = redisClient.get(secKey+"salario");
        String departamento = redisClient.get(secKey+"departamento");
        List<String> dependentes = redisClient.lrange(secKey+"dependentes", 0, -1);
        String grau_escolaridade = redisClient.get(secKey+"grau_escolaridade");

        Secretario secretario = new Secretario();

        secretario.setId(id);
        secretario.setNome(nome);
        secretario.setEndereco(endereco);
        secretario.setSexo(sexo);
        secretario.setDataNascimento(data_nascimento);
        secretario.setSalario(salario);
        secretario.setDepartamento(departamento);
        secretario.setDependentes(dependentes);
        secretario.setGrauEscoladidade(grau_escolaridade);

        return secretario;
    }

    private void save(Secretario secretario, String secKey) {

        redisClient.set(secKey+"nome", secretario.getNome());

        redisClient.set(secKey+"endereco", secretario.getEndereco());

        redisClient.set(secKey+"sexo", secretario.getSexo());

        redisClient.set(secKey+"data_nascimento", secretario.getDataNascimento());

        redisClient.set(secKey+"salario", String.valueOf(secretario.getSalario()));

        redisClient.set(secKey+"departamento", secretario.getDepartamento());

        for(String dependente: secretario.getDependentes()) {
            redisClient.rpush(secKey+"dependentes", dependente);
        }

        redisClient.set(secKey+"grau_escolaridade", secretario.getGrauEscoladidade());
    }
}
