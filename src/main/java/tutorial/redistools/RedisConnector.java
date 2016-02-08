package tutorial.redistools;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RedisConnector {

	private int port;
	private String host;
	private JedisPool pool;
	private String password;
	// private Jedis client;
	private String name;

	public RedisConnector() {
		try {
			configParameters();
		} catch(Exception e) {
			System.err.println("ERROR IN RedisConnector.java");
			e.printStackTrace(System.err);
		}
		pool = new JedisPool(new JedisPoolConfig(), this.host, this.port, 2000, this.password);	// timeout -1 ?
		// System.out.println("hostname = " + this.host);
		// System.out.println("port = " + this.port);
		// System.out.println("pass = " + this.password);
		// this.client = new Jedis(this.host, this.port);
		// this.client.auth(this.password);	// authenticate with server

	}

	public JedisPool getPool() {
		return pool;
	}

	private void configParameters() throws Exception {
		Map<String, String> env = System.getenv();

		if (env.containsKey("VCAP_SERVICES")) {
            // we are running on cloud foundry, let's grab the service details from vcap_services
            JSONParser parser = new JSONParser();
            JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
            JSONObject service = null;

            // We don't know exactly what the service is called, but it will contain "postgresql"
            for (Object key : vcap.keySet()) {
                String keyStr = (String) key;
                if (keyStr.toLowerCase().contains("redis")) {
                    service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
                    break;
                }
            }

            if (service != null) {
                JSONObject creds = (JSONObject) service.get("credentials");
                this.name = (String) creds.get("name");
                this.host = (String) creds.get("host");
                long port = (Long) creds.get("port");
				this.port = (int) port;
                this.password = (String) creds.get("password");
            } else {
				throw new Exception("No Redis service found. Make sure you have bound the correct services to your app.");
			}
        } else {
			throw new Exception("Environment Variable VCAP_SERVICES not found. Check your environment.");
		}


	}

}
