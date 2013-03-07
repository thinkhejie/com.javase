package j.se.jdbc.service;

import j.se.jdbc.dao.UserDao;

/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public class UserService {
	private UserDao userDao;

	public void regist(j.se.jdbc.domain.User user) {
		userDao.addUser(user);
		// sendMail.send(user);
	}
}
