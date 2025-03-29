package lab5.utility.validators.stringvalidators;

import lab5.utility.validators.Validator;

/**
 * Проверка корректности ключа объекта коллекции, лежащего в строке.
 * @author Alina
 */
public class KeyValidator implements Validator<String>{
    @Override
    public boolean validate(String keyString) {
        try {
            Integer key = Integer.valueOf(keyString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
