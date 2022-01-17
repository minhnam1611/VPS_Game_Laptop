//package com.example.vps_game_flatform.Config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//import javax.sql.DataSource;
//
//@Configuration
////@EnableJpaRepositories(basePackages = {"${spring.data.jpa.repository.package}"})
//public class DatasourceConfig {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix="app.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="security.datasource")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}
