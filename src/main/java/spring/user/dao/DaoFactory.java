package spring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    // ConnectionMaker의 분리
    @Bean
    public ConnectionMaker connectionMaker() {
        return new AConnectionMaker();
    }
}
