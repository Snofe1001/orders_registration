package ru.requests_registration.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${app.postgresql.url}")
    private String url;

    @Value("${app.postgresql.user}")
    private String user;

    @Value("${app.postgresql.password}")
    private String password;

    @Bean(name = "flyway-auth")
    public Flyway flywaySchemaAuth() {
        return Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:migration/auth")
                .schemas("auth")
                .load();
    }

    @Bean(name = "flyway-auth-initializer")
    public FlywayMigrationInitializer flywaySchemaAuthInitializer(
            @Qualifier("flyway-auth") Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null);
    }
}
