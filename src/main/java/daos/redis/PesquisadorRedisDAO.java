package daos.redis;

import daos.PesquisadorDAO;
import models.Pesquisador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PesquisadorRedisDAO extends GenericRedisDAO<Pesquisador> implements PesquisadorDAO {

    public PesquisadorRedisDAO() {
        super("pesquisadores");
    }

    @Override
    public void insert(Pesquisador pesquisador) {
        String pesqKey = "pesquisador:"+pesquisador.getId()+":";
        redisClient.set(pesqKey+"id", String.valueOf(pesquisador.getId()));
        save(pesquisador, pesqKey);
    }

    @Override
    public void update(Pesquisador pesquisador) {
        // deletar coleções para que possam ser inseridas novamente
        String pesqKey = "secretarios:"+pesquisador.getId()+":";
        redisClient.del(pesqKey+"endereco");
        redisClient.del(pesqKey+"sexo");
        redisClient.del(pesqKey+"data_nascimento");
        redisClient.del(pesqKey+"salario");
        redisClient.del(pesqKey+"departamento");
        redisClient.del(pesqKey+"dependentes");
        redisClient.del(pesqKey+"area_atuacao");
        redisClient.del(pesqKey+"projetos");
        save(pesquisador, pesqKey);
    }

    @Override
    public Pesquisador find(Object id) {
        String pesqKey = "pesquisadores:"+id+":";
        if(redisClient.get(pesqKey+"id") == null) return null;
        return fromRedis((Long) id, pesqKey);
    }

    @Override
    public List<Pesquisador> findAll() {

        List<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
        Set<String> keys = redisClient.keys("pesquisadores:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String pesqKey = "pesquisadores:"+id+":";
            pesquisadores.add(fromRedis(id, pesqKey));
        }
        return pesquisadores;
    }

    private Pesquisador fromRedis(Long id, String pesqKey) {

        String nome = redisClient.get(pesqKey+"nome");
        String endereco = redisClient.get(pesqKey+"endereco");
        String sexo = redisClient.get(pesqKey+"sexo");
        String data_nascimento = redisClient.get(pesqKey+"data_nascimento");
        String salario = redisClient.get(pesqKey+"salario");
        String departamento = redisClient.get(pesqKey+"departamento");
        List<String> dependentes = redisClient.lrange(pesqKey+"dependentes", 0, -1);
        String area_atuacao = redisClient.get(pesqKey+"area_atuacao");
        List<String> projetos = redisClient.lrange(pesqKey+"projetos", 0, -1);

        Pesquisador pesquisador = new Pesquisador();

        pesquisador.setId(id);
        pesquisador.setNome(nome);
        pesquisador.setEndereco(endereco);
        pesquisador.setSexo(sexo);
        pesquisador.setDataNascimento(data_nascimento);
        pesquisador.setSalario(salario);
        pesquisador.setDepartamento(departamento);
        pesquisador.setDependentes(dependentes);
        pesquisador.setAreaAtuacao(area_atuacao);
        pesquisador.setProjetos(projetos);

        return pesquisador;
    }

    private void save(Pesquisador pesquisador, String pesqKey) {

        redisClient.set(pesqKey+"nome", pesquisador.getNome());

        redisClient.set(pesqKey+"endereco", pesquisador.getEndereco());

        redisClient.set(pesqKey+"sexo", pesquisador.getSexo());

        redisClient.set(pesqKey+"data_nascimento", pesquisador.getDataNascimento());

        redisClient.set(pesqKey+"salario", String.valueOf(pesquisador.getSalario()));

        redisClient.set(pesqKey+"departamento", pesquisador.getDepartamento());

        for(String dependente: pesquisador.getDependentes()) {
            redisClient.rpush(pesqKey+"dependentes", dependente);
        }

        redisClient.set(pesqKey+"area_atuacao", pesquisador.getAreaAtuacao());

        for(String projeto: pesquisador.getProjetos()) {
            redisClient.rpush(pesqKey+"projetos", projeto);
        }
    }
}
