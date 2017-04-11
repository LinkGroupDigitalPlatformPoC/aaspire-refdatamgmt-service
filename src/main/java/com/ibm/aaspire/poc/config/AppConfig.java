package com.ibm.aaspire.poc.config;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


//TODO DOing this the dirty way.
//We want to get this out of the way for now. We will extract the values doing the hard-way parsing the JSON info from VCAP_SERVICES
@Configuration
@Profile({ "dev", "test", "prod" })
public class AppConfig {

	String dbUrl;

	@Value("${jdbc-protocol}")
	String protocol;

	@Value("${jdbc-driver}")
	String driver;

	@Bean
	@SuppressWarnings("unchecked")
	public DataSource dataSource() {

		String vcap = System.getenv("VCAP_SERVICES");

		JsonParser parser = JsonParserFactory.getJsonParser();
		Map<String, Object> jsonMap = parser.parseMap(vcap);
		List<?> postgresArr = (List<?>)jsonMap.get("compose-for-postgresql");
		Map<String, ?> compose = (Map<String, Object>)postgresArr.get(0);
		Map<String, ?> creds = (Map<String, ?>)compose.get("credentials");
		dbUrl = creds.get("uri").toString(); 
		
		// Do the parsing activity here..
		
		jsonMap.get("compose-for-postgresql");
		

		String[] parts = dbUrl.split("(://|@)");
		String[] userAndPass = parts[1].split(":");
		String user = userAndPass[0];
		String pass = userAndPass[1];
		String url = parts[2];

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(protocol + "://" + url);
		dataSourceBuilder.username(user);
		dataSourceBuilder.password(pass);
		dataSourceBuilder.driverClassName(driver);
		return dataSourceBuilder.build();
	}

}
