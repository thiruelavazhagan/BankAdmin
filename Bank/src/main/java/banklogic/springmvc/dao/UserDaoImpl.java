package banklogic.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import banklogic.springmvc.model.CountMap;
import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;

public class UserDaoImpl implements UserDao {
	Random rand = new Random();
	String totalamnt = "10000";
	String accno = rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10) + ""
			+ rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);
	String pinpass = rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int register(User user) {
		int sp = 0;
		int sp1 = 0;
		String check = "select * from users where email='" + user.getEmail() + "'";
		List<User> users = jdbcTemplate.query(check, new UserMapper());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+users);
		if ( users.isEmpty()) {
			String sql = "insert into users(firstname,surname,gender,dateofbirth,nationality,email,phone,address,city,pincode,accno,pinpass) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			sp = jdbcTemplate.update(sql,
					new Object[] { user.getFirstname(), user.getSurname(), user.getGender(), user.getDateofbirth(),
							user.getNationality(), user.getEmail(), user.getPhone(), user.getAddress(), user.getCity(),
							user.getPincode(), accno, pinpass });
			String sql1 = "insert into account(email,accno,pinpass,totalamnt) values (?,?,?,?)";

			sp1 = jdbcTemplate.update(sql1, new Object[] { user.getEmail(), accno, pinpass, totalamnt });
		}
		return sp;
	}

	public int account(User user) {
		String sql = "insert into account(email,accno,pinpass,totalamnt) values (?,?,?,?)";

		return jdbcTemplate.update(sql, new Object[] { user.getEmail(), accno, pinpass, totalamnt });
	}

	public User validateUser(Login login) {
		String sql = "select * from users where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());

		return users.size() > 0 ? users.get(0) : null;
	}

	public List<User> list() {
		String sql = "SELECT * FROM users INNER JOIN account ON users.email = account.email";
		List<User> listContact = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User acc = new User();

				acc.setFirstname(rs.getString("firstname"));
				acc.setEmail(rs.getString("email"));
				acc.setAccno(rs.getString("accno"));
				acc.setTotalamnt(rs.getString("totalamnt"));

				return acc;
			}

		});

		return listContact;
	}

	public List<CountMap> clist() {
		String sql = "SELECT * FROM countries";
		List<CountMap> listCountry = jdbcTemplate.query(sql, new RowMapper<CountMap>() {

			@Override
			public CountMap mapRow(ResultSet rs, int rowNum) throws SQLException {
				CountMap acc = new CountMap();

				acc.setCountryId(rs.getString("id"));
				acc.setCountryname(rs.getString("name"));

				return acc;
			}

		});

		return listCountry;
	}

	public int addAmount(String accno, String amount) {
		String sql = "UPDATE account SET totalamnt = ? WHERE accno =  ?";
		return jdbcTemplate.update(sql, amount, accno);

	}

	class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();

			user.setFirstname(rs.getString("firstname"));
			user.setSurname(rs.getString("surname"));
			user.setGender(rs.getString("gender"));
			user.setDateofbirth(rs.getString("dateofbirth"));
			user.setNationality(rs.getString("nationality"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setCity(rs.getString("city"));
			user.setPincode(rs.getString("pincode"));

			return user;
		}
	}

}
