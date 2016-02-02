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
		// pool = conn.getPool();
		this.jedis = conn.getConnection();
	}

	public void add(int score, String member) {
		jedis.zadd(keyname, score, member);
	}

	public int getScore(String member) {
		// Jedis jedis = null;
		double score = -1;
		score = jedis.zscore(keyname, member);

		return (int)score;
	}

	public void incrementScore(String member) {
		// Jedis jedis = null;
		jedis.zincrby(keyname, 1, member);
	}

	public void deleteSet() {
		jedis.del(keyname);
	}

	public Set<String> sortDesc() {
		// Jedis jedis = null;
		Set<String> s = null;
		s = jedis.zrevrange(keyname, 0, -1);

		return s;
	}

	// public boolean hasContent() {
	// 	Jedis jedis = null;
	// 	long s;
	// 	try {
	// 		jedis = pool.getResource();
	// 		s = jedis.zcard(keyname);
	// 	} finally {
	// 		jedis.close();
	// 	}
	//
	// 	if (s != 0) {
	// 		return true;
	// 	}
	// 	return false;
	// }

}
