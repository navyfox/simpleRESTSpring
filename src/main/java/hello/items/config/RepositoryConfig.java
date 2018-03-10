package hello.items.config;

import hello.items.repository.DatabaseItemsRepository;
import hello.items.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
public class RepositoryConfig {

    @Bean
    public ItemsRepository itemsRepository(
            @Qualifier("itemsJdbcOperations")
                    JdbcOperations jdbcOperations) {
        return new DatabaseItemsRepository(jdbcOperations);
    }

}


