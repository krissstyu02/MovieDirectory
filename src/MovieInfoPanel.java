import javax.swing.*;
import java.awt.*;

public class MovieInfoPanel extends JPanel {
    private JTextArea titleArea;
    private JTextArea descriptionArea;
    private JLabel coverLabel;
    private MovieDatabase movieDatabase;

    public MovieInfoPanel() {
        //устанавливаем layot
        setLayout(new BorderLayout());
        setBackground(Color.GRAY); // Установка фона панели
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Установка отступов

        movieDatabase = new MovieDatabase();

        titleArea = new JTextArea("    Добро пожаловать в каталог фильмов!\n \n" +
                                   "Начнем поиск и выбор лучших фильмов для вас. " +
                "Просто выберите фильм из списка слева,чтобы узнать больше." +
                "Вы также можете воспользоваться поисковой функцией," +
                "чтобы быстро найти фильмы по названию.\n");
        //нельзя редактировать
        titleArea.setEditable(false);
        titleArea.setBackground(Color.GRAY);
        titleArea.setFont(new Font("Arial", Font.BOLD, 20)); // Установка шрифта и размера
        titleArea.setForeground(Color.WHITE); // Установка цвета текста
        titleArea.setWrapStyleWord(true); // Перенос слов
        titleArea.setLineWrap(true);//перенос строк

        descriptionArea = new JTextArea();
        descriptionArea.setBackground(Color.GRAY);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Установка шрифта и размера
        descriptionArea.setForeground(Color.WHITE); // Установка цвета текста

        coverLabel = new JLabel();
        coverLabel.setHorizontalAlignment(JLabel.CENTER);
        displayMovieCover("images/main2.jpg",0);

        add(titleArea, BorderLayout.NORTH);
        add(coverLabel, BorderLayout.CENTER);
        add(descriptionArea, BorderLayout.SOUTH);
    }

    //добавляем данные из бд при нажатии на имя фильма
    public void displayMovieInfo(String movieName) {
        Movie movie = movieDatabase.getMovieByName(movieName);
        if (movie != null) {
            titleArea.setText(movie.getTitle()+"\n");
            descriptionArea.setRows(10);
            descriptionArea.setColumns(40);
            descriptionArea.setText(
                    "\n                                   Описание фильма\n" + movie.getDescription());
            displayMovieCover(movie.getCoverPath(),1);
        }
    }

    public void displayMovieCover(String coverPath,int flag) {
        if (coverPath != null && !coverPath.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(coverPath);
                Image image = icon.getImage();
                //картинка из бд
                if (flag==1){
                    Image scaledImage = image.getScaledInstance(550, 300, Image.SCALE_SMOOTH);
                }
                //картинка начальной страницы
                Image scaledImage = image.getScaledInstance(550, 450, Image.SCALE_SMOOTH);
                coverLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                // Обработка ошибки при загрузке картинки
                e.printStackTrace();
                //пустая страница
                coverLabel.setIcon(null); // Очистить JLabel, если произошла ошибка
            }
        } else {
            //пустая страница
            coverLabel.setIcon(null);
        }
    }
}
