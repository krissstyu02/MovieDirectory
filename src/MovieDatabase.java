import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private Connection connection;

    public MovieDatabase() {
        try {
            // Загрузка драйвера JDBC для SQLite
            Class.forName("org.sqlite.JDBC");

            // Установление соединения с базой данных SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:film.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение списка всех фильмов из базы данных
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            String query = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setCoverPath(resultSet.getString("cover_path"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Получение информации о фильме по его названию
    public Movie getMovieByName(String movieName) {
        try {
            String query = "SELECT * FROM movies WHERE title = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, movieName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setCoverPath(resultSet.getString("cover_path"));
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
