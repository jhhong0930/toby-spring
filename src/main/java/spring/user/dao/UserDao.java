package spring.user.dao;

import lombok.RequiredArgsConstructor;
import spring.user.domain.User;

import java.sql.*;

@RequiredArgsConstructor
public class UserDao {

    /**
     * 클래스를 분리함으로써 서로 관심사가 다르고 변화의 성격이 다른 구 코드를 완벽히 분리해냈다
     * 하지만, UserDao가 SimpleConnectionMaker에 종속적이므로 코드의 수정없이 서브 클래스에 커넥션 생성 기능을 변경할 수 없다
     * UserDao는 커넥션을 가져오는 구체적인 방법에 종속되어 버린다
     */
    private SimpleConnectionMaker simpleConnectionMaker;

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = simpleConnectionMaker.makeNewConnection();

        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection conn = simpleConnectionMaker.makeNewConnection();

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
