package th.co.cana.main;

import th.co.cana.framework.utils.Validators;

import java.util.Optional;

/**
 * @author supot.jdev
 * @version 1.0
 */
public class ValidatorsTest {
    public static void main(String[] args) {
        Optional<String> op = Optional.of("11");
        System.out.println("op : " + Validators.isEmpty(op));
        op = Optional.empty();
        System.out.println("op : " + Validators.isEmpty(op));
    }
}
