package ru.javabegin.training.spring.dao.impls;

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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.javabegin.training.spring.dao.interfaces.MP3Dao;
import ru.javabegin.training.spring.dao.objects.Author;
import ru.javabegin.training.spring.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private static final String mp3Table = "mp3_files";
	private static final String mp3View = "mp3_view";

	private SimpleJdbcInsert insertMP3;

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSourse(DataSource dataSourse) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSourse);
		this.insertMP3 = new SimpleJdbcInsert(dataSourse).withTableName("mp3_files").usingColumns("name", "author");
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional
	public int insert(MP3 mp3) {
		String sqlInsertAuthor = "insert into author (name) VALUES (:authorName)";

		Author author = mp3.getAuthor();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("authorName", author.getName());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(sqlInsertAuthor, params, keyHolder);

		int author_id = keyHolder.getKey().intValue();

		String sqlInserMP3 = "insert into mp3_files (author_id, name) VALUES (:authorId, :mp3Name)";

		params = new MapSqlParameterSource();
		params.addValue("mp3Name", mp3.getName());
		params.addValue("authorId", author_id);

		return jdbcTemplate.update(sqlInserMP3, params);
	}

	public int insertList(List<MP3> listMP3) {
		// String sql = "insert into mp3_files (name, author) VALUES (:name,
		// :author)";
		// SqlParameterSource[] batch =
		// SqlParameterSourceUtils.createBatch(listMP3.toArray());
		// int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
		// return updateCounts.length;

		int i = 0;

		for (MP3 mp3 : listMP3) {
			insert(mp3);
			i++;
		}

		return i;
	}

	// public void insertWithJDBC(MP3 mp3) {
	//
	// Connection connection = null;
	//
	// try {
	// Class.forName("org.sqlite.JDBC");
	// String url = "jdbc:sqlite:db/spring_database.db";
	// connection = DriverManager.getConnection(url, "", "");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// String sql = "insert into mp3_files (name, author) VALUES (?, ?)";
	//
	// try {
	// PreparedStatement pStatement = connection.prepareStatement(sql);
	// pStatement.setString(1, mp3.getName());
	// pStatement.setString(2, mp3.getAuthor());
	// pStatement.executeUpdate();
	// pStatement.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// if (connection != null) {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	//
	// }
	// }
	// }
	// }

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
		String sql = "select * from " + mp3View + " where mp3_id =:mp3_id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mp3_id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByName(String name) {
		String sql = "select * from " + mp3View + " where upper(mp3_name) like :mp3_name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mp3_name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "select * from " + mp3View + " where upper(author_name) like :author_name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author_name", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public int getMP3Count() {
		String sql = "select count(*) from " + mp3Table;
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	private static final class MP3RowMapper implements RowMapper<MP3> {

		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author author = new Author();
			author.setId(rs.getInt("author_id"));
			author.setName("author_name");

			MP3 mp3 = new MP3();
			mp3.setId(rs.getInt("id"));
			mp3.setName(rs.getString("name"));
			mp3.setAuthor(author);
			return mp3;
		}

	}

	public Map<String, Integer> getStat() {
		String sql = "select author_name, count(*) as count from " + mp3View + " group by author_name";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap<String, Integer>();
				while (rs.next()) {
					String author = rs.getString("author_name");
					int count = rs.getInt("count");
					map.put(author, count);
				}
				return map;
			}
		});
	}
}
