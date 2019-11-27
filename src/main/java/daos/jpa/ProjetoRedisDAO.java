package daos.jpa;

import daos.ProjetoDAO;
import models.Funcionario;
import models.Projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProjetoRedisDAO extends GenericRedisDAO<Projeto> implements ProjetoDAO {

    public ProjetoRedisDAO() {
        super("projetos");
    }

    @Override
    public void insert(Projeto projeto) {
        String funcKey = "projetos:"+projeto.getNumero()+":";
        redisClient.set(funcKey+"id", String.valueOf(projeto.getNumero()));
        save(projeto, funcKey);
    }

    @Override
    public void update(Projeto projeto) {
        // deletar coleções para que possam ser inseridas novamente
        String funcKey = "projetos:"+projeto.getNumero()+":";
        redisClient.del(funcKey+"horas_duracao");
        redisClient.del(funcKey+"departamento");
        redisClient.del(funcKey+"pesquisadores");
        save(projeto, funcKey);
    }

    @Override
    public Projeto find(Object id) {
        String funcKey = "projetos:"+id+":";
        if(redisClient.get(funcKey+"id") == null) return null;
        return fromRedis((Long) id, funcKey);
    }

    @Override
    public List<Projeto> findAll() {

        List<Projeto> projetos = new ArrayList<Projeto>();
        Set<String> keys = redisClient.keys("projetos:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "projetos:"+id+":";
            projetos.add(fromRedis(id, funcKey));
        }
        return projetos;
    }

    private Projeto fromRedis(Long id, String funcKey) {

        String nome = redisClient.get(funcKey+"nome");
        String horas_duracao = redisClient.get(funcKey+"horas_duracao");
        String departamento = redisClient.get(funcKey+"departamento");
        List<String> pesquisadores = redisClient.lrange(funcKey+"pesquisadores", 0, -1);

        Projeto projeto = new Projeto();

        projeto.setNumero(id);
        projeto.setNome(nome);
        projeto.setHorasDuracao(Integer.parseInt(horas_duracao));
        projeto.setDepartamento(departamento);
        projeto.setPesquisadores(pesquisadores);

        return projeto;
    }

    private void save(Projeto projeto, String projKey) {

        redisClient.set(projKey+"nome", projeto.getNome());

        redisClient.set(projKey+"horas_duracao", String.valueOf(projeto.getHorasDuracao()));

        redisClient.set(projKey+"departamento", projeto.getDepartamento());

        for(String pesquisador: projeto.getPesquisadores()) {
            redisClient.rpush(projKey+"pesquisadores", pesquisador);
        }

    }
}
