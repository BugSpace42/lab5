package lab5.entity;

/**
 * Перечисление музыкальных жанров.
 * @author Alina
 */
public enum MusicGenre {
    ROCK,
    HIP_HOP,
    JAZZ,
    POP,
    PUNK_ROCK;

    /**
     * Возвращает строку для запроса объекта класса MusicGenre у пользователя. 
     * @return строка для запроса объекта у пользователя
     */
    public static String askGenreString() {
        return "Введите музыкальный жанр: ";
    }

    /**
     * @return Строка со всеми элементами enum'а через запятую.
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (MusicGenre genre : values()) {
            nameList.append(genre.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}