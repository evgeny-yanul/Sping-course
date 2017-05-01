package ru.javabegin.training.spring.dao.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.dao.interfaces.MP3Dao;
import ru.javabegin.training.spring.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(MP3 mp3) {
		String sql = "insert into mp3_files (name, author) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { mp3.getName(), mp3.getAuthor() });
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

		String sql = "insert into MP3 (name, author) VALUES (?, ?)";

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

	public void delete(MP3 mp3) {
		// TODO Auto-generated method stub

	}

	public MP3 getMP3ByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MP3> getMP3ListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MP3> getMP3ListByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
