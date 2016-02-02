package redistools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {

	private String ipadd;
	private int port;
	private String host;
	private JedisPool pool;
	private String password;

	public RedisConnector() {
		host = configHost();
		pool = new JedisPool(new JedisPoolConfig(), host);
		// alt
		Jedis j2 = new Jedis(host, port);
		j2.auth(password);

	}

	// public JedisPool getPool() {
	// 	return pool;
	// }
	//
	// private String configHost() {
	// 	// do fix: use vcap services
	// 	return "localhost";
	//
	// }

	private void configServices() {
		CloudEnvironment environment = new CloudEnvironment();
		if ( environment.getServiceDataByLabels("redis").size() == 0 ) {
			throw new Exception( "No Redis service is bund to this app!!" );
		}

		Object[] info = new Object[3];
		Map credential = (Map)((Map)environment.getServiceDataByLabels("redis").get(0)).get( "credentials" );

		this.host = (String)credential.get( "host" );
		this.port = (Integer)credential.get( "port" );
		this.password = (String)credential.get( "password" );
	}

}
