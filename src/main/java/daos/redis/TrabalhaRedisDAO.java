package daos.redis;

import daos.TrabalhaDAO;
import models.Trabalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrabalhaRedisDAO extends GenericRedisDAO<Trabalha> implements TrabalhaDAO {

    public TrabalhaRedisDAO() {
        super("trabalha");
    }

    @Override
    public void insert(Trabalha trabalha) {
        String trabalhaKey = "trabalha:" + trabalha.getId() + ":";
        redisClient.set(trabalhaKey + "id", String.valueOf(trabalha.getId()));
        save(trabalha, trabalhaKey);
    }

    @Override
    public void update(Trabalha trabalha) {
        String trabKey = "trabalha:" + trabalha.getId() + ":";
        redisClient.del(trabKey + "pesquisador");
        redisClient.del(trabKey + "departamento");
        redisClient.del(trabKey + "horas_semanais_trabalhadas");
        save(trabalha, trabKey);
    }

    @Override
    public Trabalha find(Object id) {
        String trabKey = "trabalha:" + id + ":";
        if (redisClient.get(trabKey + "id") == null) return null;
        return fromRedis((Long) id, trabKey);
    }

    @Override
    public List<Trabalha> findAll() {
        List<Trabalha> trabalha = new ArrayList<Trabalha>();
        Set<String> keys = redisClient.keys("trabalha:*:id");

        for (String key : keys) {
            Long id = Long.parseLong(redisClient.get(key));
            String funcKey = "trabalha:" + id + ":";
            trabalha.add(fromRedis(id, funcKey));
        }

        return trabalha;
    }

    private Trabalha fromRedis(Long id, String trabKey) {
        String pesquisador = redisClient.get(trabKey + "pesquisador");
        String projeto = redisClient.get(trabKey + "projeto");
        String horasSemanaisTrabalhadas = redisClient.get(trabKey + "horas_semanais_trabalhadas");

        Trabalha trabalha = new Trabalha();
        trabalha.setId(id);
        trabalha.setPesquisador(pesquisador);
        trabalha.setProjeto(projeto);
        trabalha.setHorasSemanaisTrabalhadas(Integer.parseInt(horasSemanaisTrabalhadas));

        return trabalha;
    }

    private void save(Trabalha trab, String trabKey) {
        redisClient.set(trabKey + "pesquisador", trab.getPesquisador());
        redisClient.set(trabKey + "projeto", String.valueOf(trab.getProjeto()));
        redisClient.set(trabKey + "horas_semanais_trabalhadas", String.valueOf(trab.getHorasSemanaisTrabalhadas()));
    }
}
