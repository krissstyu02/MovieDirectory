import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieInfoPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JLabel coverLabel;
    private MovieDatabase movieDatabase;

    public MovieInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Установка фона панели
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Установка отступов

        movieDatabase = new MovieDatabase();

        titleLabel = new JLabel();
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Установка шрифта и размера
        titleLabel.setForeground(Color.WHITE); // Установка цвета текста

        descriptionArea = new JTextArea(10, 40);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Установка шрифта и размера
        descriptionArea.setForeground(Color.WHITE); // Установка цвета текста

        coverLabel = new JLabel();
        coverLabel.setHorizontalAlignment(JLabel.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(coverLabel, BorderLayout.CENTER);
        add(descriptionArea, BorderLayout.SOUTH);
    }

    public void displayMovieInfo(String movieName) {
        Movie movie = movieDatabase.getMovieByName(movieName);
        if (movie != null) {
            titleLabel.setText(movie.getTitle());
            descriptionArea.setText("Описание фильма: " + movie.getDescription());
            displayMovieCover(movie.getCoverPath());
        }
    }

    public void displayMovieCover(String coverPath) {
        if (coverPath != null && !coverPath.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(coverPath);
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(410, 300, Image.SCALE_SMOOTH);
                coverLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                // Обработка ошибки при загрузке картинки
                e.printStackTrace();
                coverLabel.setIcon(null); // Очистить JLabel, если произошла ошибка
            }
        } else {
            coverLabel.setIcon(null);
        }
    }
}
