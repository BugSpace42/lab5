package lab5.utility.validators.stringvalidators.musicband.coordinates;

import lab5.utility.validators.Validator;

/**
 * Проверка корректности координаты x.
 * @author Alina
 */
public class CoordXValidator implements Validator<String>{
    @Override
    public boolean validate(String xString) {
        try {
            @SuppressWarnings("unused")
            Integer x = Integer.valueOf(xString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
