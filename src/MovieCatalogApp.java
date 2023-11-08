import javax.swing.*;
import java.awt.*;

public class MovieCatalogApp extends JFrame {
    private MovieListPanel movieListPanel;
    private MovieInfoPanel movieInfoPanel;
    private JTextField searchField;
    private JButton searchButton;

    public MovieCatalogApp() {
        setTitle("Каталог фильмов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 750);

        //панели
        movieInfoPanel = new MovieInfoPanel();
        movieListPanel = new MovieListPanel(movieInfoPanel);

        // Создаем панель для компонентов
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //располагаем наши компоненты
        mainPanel.add(movieListPanel, BorderLayout.WEST);
        mainPanel.add(movieInfoPanel, BorderLayout.CENTER);

        //поле поиска
        searchField = new JTextField();
        searchField = new JTextField(20);

        searchButton = new JButton("Поиск");
        searchButton.setBackground(Color.WHITE); // Установка цвета фона кнопки
        searchButton.setForeground(Color.GRAY);

        //обработчик лямбда-функция
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            movieListPanel.searchMovies(searchText);
        });

        // Создаем панель для компонентов поиска
        JPanel searchPanel = new JPanel();
        //создаем flowlayot
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(Color.GRAY);
        searchPanel.setForeground(Color.WHITE); // Установка цвета текста
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Добавляем панель поиска в верхнюю часть главного окна
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        //специальный поток, предназначенный для обработки событий интерфейсм
        SwingUtilities.invokeLater(() -> {
            MovieCatalogApp app = new MovieCatalogApp();
            app.setVisible(true);
        });
    }
}
