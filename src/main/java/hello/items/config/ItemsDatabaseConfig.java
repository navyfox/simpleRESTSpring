package hello.items.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ItemsDatabaseConfig {
    @Bean
    @Qualifier("itemsDataSource")
    @ConfigurationProperties(
            prefix = "spring.datasource.items")
    public DataSource itemsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("itemsJdbcOperations")
    public JdbcOperations itemsJdbcOperations(
            @Qualifier("itemsDataSource")
                    DataSource itemsDataSource
    ) {
        return new JdbcTemplate(itemsDataSource);
    }
}
