package hello.items.config;

import hello.items.repository.ItemsRepository;
import hello.items.repository.SampleItemsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ItemsRepository itemsRepository() {
        return new SampleItemsRepository();
    }

}

