package spring.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstactUserDao {

    /**
     * UserDao의 관심사 - Connection 기능 제공
     * NUserDao, DUserDao의 관심사 - 어떤 방법으로 Connection 오브젝트를 만들어 낼 것인지
     * - 이처럼 슈퍼클래스에 기본 로직의 흐름을 만든 후 각 서브클래스에서 필요에 맞게 구현하여 사용하는 방법을 템플릿 메소드 패턴이라 한다
     * - 또한 서브클래스에서 구체적인 오브젝트 생성 방법을 결정하게 하는 것을 펙토리 메소드 패턴이라고도 한다
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
