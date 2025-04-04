package lab5.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lab5.entity.MusicBand;

/**
 * Класс, который оперирует коллекцией.
 * @author Alina
 */
public class CollectionManager {
    private static HashMap<Integer, MusicBand> collection;
    private final Date initDate;

    public CollectionManager(HashMap<Integer, MusicBand> collection) {
        CollectionManager.collection = collection;
        initDate = new Date();
    }

    /**
    * @return коллекция
    */
    public HashMap<Integer, MusicBand> getCollection() {
        return collection;
    }

    /**
     * Возвращает элемент коллекции с заданным id.
     * @param id id элемента
     * @return элемент коллекции
     */
    public MusicBand getById(Long id) {
        for (HashMap.Entry<Integer, MusicBand> entry : collection.entrySet()) {
          if (entry.getValue().getId().equals(id)) return entry.getValue();
        }
        return null;
      }

    /**
     * Добавляет элемент в коллекцию.
     * @param musicBand добавляемый объект
     */
    public void addToCollection(Integer key, MusicBand musicBand) {
        collection.put(key, musicBand);
    }

    /**
     * Обновляет элемент коллекции, id которого равен заданному.
     * @param id id элемента, значение которого нужно обновить
     * @param musicBand новое значение элемента
     */
    public void updateElementById(Long id, MusicBand musicBand) {
        for (HashMap.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getId().equals(id)) collection.put(entry.getKey(), musicBand);
        }
    }

    /**
     * Удаляет элемент из коллекции по его ключу.
     * @param key ключ элемента, который нужно удалить
     */
    public void removeByKey(Integer key) {
        collection.remove(key);
    }

    /**
     * Очищает коллекцию.
     */
    public void clearCollection() {
        collection.clear();
    }

    /**
     * Возвращает дату инициализации коллекции.
     * @return дата инициализации коллекции
     */
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Возвращает список id существующих элементов коллекции.
     * @return список id существующих элементов коллекции
     */
    public static List<Long> getIdList() {
        ArrayList<Long> idList = new ArrayList<>();
        for (HashMap.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            idList.add(entry.getValue().getId());
        }
        return idList;
    }

    /**
     * Задаёт новое значение полю коллекции.
     * @param collection новая коллекция.
     */
    public void setCollection(HashMap<Integer, MusicBand> collection) {
        CollectionManager.collection = collection;
    }
    
    /**
     * Возвращает строковое представление коллекции.
     * @return строковое представление коллекции
     */
    @Override
    public String toString() {
        String info = "";
        for (Map.Entry<Integer, MusicBand> elem : collection.entrySet()) {
            info += "Элемент коллекции с ключом " + elem.getKey() + ":\n";
            info += elem.getValue();
            info += "\n";
        }
        return info;
    }
}
