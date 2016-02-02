package redistools;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SetOperations {

	private String keyname;
	private Jedis jedis;


	public SetOperations(String keyname) {
		this.keyname = keyname;
		RedisConnector conn = new RedisConnector();
		this.jedis = conn.getConnection();
	}

	public void add(int score, String member) {
		jedis.zadd(keyname, score, member);
	}

	public int getScore(String member) {
		double score = -1;
		score = jedis.zscore(keyname, member);

		return (int)score;
	}

	public void incrementScore(String member) {
		jedis.zincrby(keyname, 1, member);
	}

	public void deleteSet() {
		jedis.del(keyname);
	}

	public Set<String> sortDesc() {
		Set<String> s = null;
		s = jedis.zrevrange(keyname, 0, -1);

		return s;
	}

}
