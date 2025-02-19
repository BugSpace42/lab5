package lab5.entity;

/**
 * Класс координат.
 * @author Alina
 */
public class Coordinates {
    private final Integer x; //Поле не может быть null
    private final long y; //Значение поля должно быть больше -973

    /**
     * Конструктор - создание нового объекта с заданными x и y.
     * @param x - первая координата
     * @param y - вторая координата
     */
    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает строку с названием поля (например для запроса поля у пользователя). 
     * @return строка с названием поля
     */
    public static String getXName() {
        return "первая координата (x)";
    }

    /**
     * Возвращает строку с названием поля (например для запроса поля у пользователя). 
     * @return строка с названием поля
     */
    public static String getYName() {
        return "вторая координата (y)";
    }

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    /**
     * Проверка равенcтва двух координат.
     * @param coordinates - координаты сравниваемого объекта
     * @return true - если объекты одинаковые, false - если разные
     */
    public boolean equals(Coordinates coordinates) {
        if (coordinates == this) return true;
        return coordinates.x.equals(this.x) && coordinates.y == this.y;
    }
    
    /**
     * @return Строковое представление объекта класса.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}