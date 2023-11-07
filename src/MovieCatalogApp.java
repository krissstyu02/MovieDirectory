import javax.swing.*;
import java.awt.*;

public class MovieCatalogApp extends JFrame {
    private MovieListPanel movieListPanel;
    private MovieInfoPanel movieInfoPanel;

    public MovieCatalogApp() {
        setTitle("Топ 15 лучших фильмов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);

        movieInfoPanel = new MovieInfoPanel(); // Создайте сначала MovieInfoPanel
        movieListPanel = new MovieListPanel(movieInfoPanel);

        // Создаем панель для компонентов
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK); // Черный фон

        // Добавим описание приложения
        JLabel descriptionLabel = new JLabel("Каталог лучших фильмов");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(descriptionLabel, BorderLayout.NORTH);

        mainPanel.add(movieListPanel, BorderLayout.WEST);
        movieListPanel.setBackground(Color.BLACK);
        mainPanel.add(movieInfoPanel, BorderLayout.CENTER);

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
