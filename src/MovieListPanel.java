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

        //компонент для отображения список
        movieList = new JList<>(movieListModel);
        //в списке может быть выделена только одна строка.
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Установка цвета, шрифта и добавление обработчика событий для выбора фильма
        movieList.setForeground(Color.WHITE);
        movieList.setBackground(Color.GRAY);
        movieList.setFont(new Font("Arial", Font.PLAIN, 14));

        //обработчик лямбда-функция
        movieList.addListSelectionListener(e -> {
            String selectedMovie = getSelectedMovie();
            infoPanel.displayMovieInfo(selectedMovie);
        });
        //создание панели прокрутки для списка movieList
        JScrollPane movieListScrollPane = new JScrollPane(movieList);
        add(movieListScrollPane, BorderLayout.WEST);
        movieInfoPanel = infoPanel;
    }

    public void searchMovies(String searchText) {
        //создание модели списка
        DefaultListModel<String> filteredListModel = new DefaultListModel<>();
        for (int i = 0; i < movieListModel.size(); i++) {
            //получаем название фильма по индексу
            String movieTitle = movieListModel.getElementAt(i);
            if (movieTitle.toLowerCase().contains(searchText.toLowerCase())) {
                filteredListModel.addElement(movieTitle);
            }
        }
        //заменяем данные в списке на соответсвующие
        movieList.setModel(filteredListModel);
    }

    public String getSelectedMovie() {
        return movieList.getSelectedValue();
    }
}
