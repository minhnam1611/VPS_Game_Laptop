package com.example.vps_game_flatform.Config;

import com.example.vps_game_flatform.Entity.system.SysMenu;
import com.example.vps_game_flatform.Entity.system.SysResource;
import com.example.vps_game_flatform.Entity.system.SysRole;
import com.example.vps_game_flatform.Entity.system.SysUser;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration

@EnableJpaRepositories(basePackages ="com.example.vps_game_flatform.DAO.system",
        entityManagerFactoryRef = "systemEntityManagerFactory",
        transactionManagerRef= "systemTransactionManager"
)
public class ConfigSystemDatasource {
    @Bean
    @ConfigurationProperties("app.datasource.system")
    public DataSourceProperties systemDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.system.configuration")
    public DataSource systemDataSource() {
        return systemDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "systemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean systemEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(systemDataSource())
                .packages(SysResource.class)
                .packages(SysUser.class)
                .packages(SysRole.class)
                .packages(SysMenu.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager systemTransactionManager(
            final @Qualifier("systemEntityManagerFactory") LocalContainerEntityManagerFactoryBean systemEntityManagerFactory) {
        return new JpaTransactionManager(systemEntityManagerFactory.getObject());
    }
}
