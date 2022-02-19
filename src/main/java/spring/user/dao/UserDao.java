package spring.user.dao;

import lombok.RequiredArgsConstructor;
import spring.user.domain.User;

import java.sql.*;

@RequiredArgsConstructor
public class UserDao {

    private final ConnectionMaker connectionMaker;

    /**
     * private 생성자를 갖고있기 떄문에 상속 불가
     * 테스트하기 힘든 싱글톤
     * 서버환경에서는 싱글톤이 하나만 만들어지는 것을 보장하지 못함
     */
//    private static UserDao INSTANCE;
//
//    private UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }
//
//    private static synchronized UserDao getInstance() {
//        if (INSTANCE == null) INSTANCE = new UserDao(???);
//        return INSTANCE;
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

}
