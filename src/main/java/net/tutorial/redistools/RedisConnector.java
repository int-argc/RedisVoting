package redistools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {
	
	private String ipadd;
	private int port;
	private String host;
	private JedisPool pool;
	
	public RedisConnector() {
		host = configHost();
		pool = new JedisPool(new JedisPoolConfig(), host);
	}
	
	public JedisPool getPool() {
		return pool;
	}
	
	private String configHost() {
		// do fix: use vcap services
		return "localhost";
		
	}
	
	

}
