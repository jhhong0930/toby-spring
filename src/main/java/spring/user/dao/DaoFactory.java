package spring.user.dao;

public class DaoFactory {

    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    // ConnectionMaker의 분리
    public ConnectionMaker connectionMaker() {
        return new AConnectionMaker();
    }
}
