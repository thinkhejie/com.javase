package j.jdbc.dao.impl;

import j.se.jdbc.JdbcUtils;
import j.se.jdbc.dao.DaoException;
import j.se.jdbc.dao.UserDao;
import j.se.jdbc.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user(name,birthday, money) values (?,?,?) ";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	@Override
	public void delete(User user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "delete from user where id=" + user.getId();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}

	@Override
	public User findUser(String loginName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, money, birthday  from user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = mappingUser(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	@Override
	public User getUser(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, money, birthday  from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = mappingUser(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setMoney(rs.getFloat("money"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	}

	@Override
	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update user set name=?, birthday=?, money=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

}
