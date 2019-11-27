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
        String projKey = "projetos:"+projeto.getNumero()+":";
        redisClient.set(projKey+"id", String.valueOf(projeto.getNumero()));
        save(projeto, projKey);
    }

    @Override
    public void update(Projeto projeto) {
        // deletar coleções para que possam ser inseridas novamente
        String projKey = "projetos:"+projeto.getNumero()+":";
        redisClient.del(projKey+"horas_duracao");
        redisClient.del(projKey+"departamento");
        redisClient.del(projKey+"pesquisadores");
        save(projeto, projKey);
    }

    @Override
    public Projeto find(Object id) {
        String projKey = "projetos:"+id+":";
        if(redisClient.get(projKey+"id") == null) return null;
        return fromRedis((Long) id, projKey);
    }

    @Override
    public List<Projeto> findAll() {

        List<Projeto> projetos = new ArrayList<Projeto>();
        Set<String> keys = redisClient.keys("projetos:*:id");
        for(String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String projKey = "projetos:"+id+":";
            projetos.add(fromRedis(id, projKey));
        }
        return projetos;
    }

    private Projeto fromRedis(Long id, String projKey) {

        String nome = redisClient.get(projKey+"nome");
        String horas_duracao = redisClient.get(projKey+"horas_duracao");
        String departamento = redisClient.get(projKey+"departamento");
        List<String> pesquisadores = redisClient.lrange(projKey+"pesquisadores", 0, -1);

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
