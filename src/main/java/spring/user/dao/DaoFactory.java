package spring.user.dao;

public class DaoFactory {

    public UserDao userDao() {
        ConnectionMaker connectionMaker = new AConnectionMaker();
        return new UserDao(connectionMaker);
    }
}
