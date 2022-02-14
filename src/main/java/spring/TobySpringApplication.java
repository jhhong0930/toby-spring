package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.user.dao.DaoFactory;
import spring.user.dao.UserDao;
import spring.user.domain.User;

import java.sql.SQLException;

public class TobySpringApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        /**
         * getBean(): ApplicationContext가 관리하는 오브젝트를 요청
         * "userDao"(name): ApplicationContext에 등록된 빈의 이름(DaoFactory의 userDao())
         * getBean()은 기본 return 타입이 Object이지만, 두 번째 파라미터에 리턴 타입을 지정할 수 있다
         */
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
