package daos.jpa;

import daos.GenericDAO;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class GenericRedisDAO<T> implements GenericDAO<T> {

    protected Jedis redisClient;
    private String tableName;

    public GenericRedisDAO(String tableName) {
        this.tableName = tableName;
        this.redisClient = RedisUtil.getClient();
    }

    @Override
    public void insert(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(Object id) {
        Set<String> keys = redisClient.keys(tableName+":"+id+":*");
        for(String key : keys) {
            redisClient.del(key);
        }
    }

    @Override
    public T find(Object id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void close() {
        RedisUtil.closeClient(this.redisClient);
    }
}
