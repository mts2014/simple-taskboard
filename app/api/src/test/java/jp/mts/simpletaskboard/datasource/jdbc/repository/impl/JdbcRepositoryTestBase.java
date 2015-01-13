package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=JdbcRepositoryTestBase.TestConfig.class)
public class JdbcRepositoryTestBase {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Configuration
	public static class TestConfig {
		@Bean
		public DataSource dataSource(){
			return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql")
				.build();
		}
		@Bean
		public JdbcTemplate jdbcTemplate(){
			return new JdbcTemplate(dataSource());
		}
	}

}
