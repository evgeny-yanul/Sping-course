package ru.javabegin.training.spring.dao.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.dao.interfaces.MP3Dao;
import ru.javabegin.training.spring.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insert(MP3 mp3) {
		String sql = "insert into mp3_files (name, author) VALUES (:name, :author)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		jdbcTemplate.update(sql, params, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public void insert(List<MP3> mp3List) {
		for (MP3 mp3 : mp3List) {
			insert(mp3);
		}
	}

	public void insertWithJDBC(MP3 mp3) {

		Connection connection = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:db/spring_database.db";
			connection = DriverManager.getConnection(url, "", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "insert into mp3_files (name, author) VALUES (?, ?)";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, mp3.getName());
			pStatement.setString(2, mp3.getAuthor());
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	public void delete(int id) {
		String sql = "delete from mp3_files where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		jdbcTemplate.update(sql, params);
	}

	public void delete(MP3 mp3) {
		// String sql = "select id FROM mp3_files WHERE name=? AND author=?";
		// String idStr = (String) jdbcTemplate.q(sql, new Object[] {
		// mp3.getName(), mp3.getAuthor() }, String.class);
		// int id = Integer.parseInt(idStr);
		// mp3.setId(id);
		delete(mp3.getId());
	}

	public MP3 getMP3ByID(int id) {
		String sql = "select * from mp3_files where id=:id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByName(String name) {
		String sql = "select * from mp3_files where upper(name) like :name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "select * from mp3_files where upper(author) like :author";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public int getMP3Count() {
		String sql = "select count(*) from mp3_files";
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	private static final class MP3RowMapper implements RowMapper<MP3> {

		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			MP3 mp3 = new MP3();
			mp3.setId(rs.getInt("id"));
			mp3.setName(rs.getString("name"));
			mp3.setAuthor(rs.getString("author"));
			return mp3;
		}

	}

	public Map<String, Integer> getStat() {
		String sql = "select author, count(*) as count from mp3_files group by author";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap<String, Integer>();
				while (rs.next()) {
					String author = rs.getString("author");
					int count = rs.getInt("count");
					map.put(author, count);
				}
				return map;
			}
		});
	}
}
