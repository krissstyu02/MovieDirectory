import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MovieListPanel extends JPanel {
    private DefaultListModel<String> movieListModel;
    private JList<String> movieList;
    private MovieDatabase movieDatabase;
    private MovieInfoPanel movieInfoPanel;

    public MovieListPanel(MovieInfoPanel infoPanel) {
        setLayout(new BorderLayout());

        movieListModel = new DefaultListModel<>();
        movieDatabase = new MovieDatabase();

        // Получение списка фильмов из базы данных и добавление их в модель списка
        List<Movie> movies = movieDatabase.getAllMovies();
        for (Movie movie : movies) {
            movieListModel.addElement(movie.getTitle());
        }

        movieList = new JList<>(movieListModel);
        JScrollPane movieListScrollPane = new JScrollPane(movieList);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(movieListScrollPane, BorderLayout.WEST);

        // Установка цвета, шрифта и добавление обработчика событий для выбора фильма
        movieList.setForeground(Color.WHITE);
        movieList.setBackground(Color.BLACK);
        movieList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Добавляем обработчик событий для выбора фильма
        movieList.addListSelectionListener(e -> {
            String selectedMovie = getSelectedMovie();
            infoPanel.displayMovieInfo(selectedMovie);
        });

        movieInfoPanel = infoPanel;
    }

    public String getSelectedMovie() {
        return movieList.getSelectedValue();
    }
}
