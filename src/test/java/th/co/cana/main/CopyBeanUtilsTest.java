package th.co.cana.main;

import th.co.cana.framework.utils.BeanUtils;
import th.co.cana.framework.utils.bean.CopyBeanOptions;
import th.co.cana.model.Dog;
import th.co.cana.model.Horse;
import th.co.cana.utils.MockUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author supot.jdev
 * @version 1.0
 */
public class CopyBeanUtilsTest {
    public static void main(String[] args) {
        copy();
        System.out.println("\n++++++++++++++++++++++++++++ List ++++++++++++++++++++++++++");
        copyList();
    }

    private static void copy() {
        System.out.println("+++++++++++++++ Original +++++++++++++");
        Horse horse = MockUtils.createHorse();
        System.out.println(horse);

        System.out.println("+++++++++++++++ Copy +++++++++++++");
        horse = BeanUtils.copyProperties(horse, Horse.class);
        System.out.println(horse);

        System.out.println("+++++++++++++++ Copy to other +++++++++++++");
        Dog dog = BeanUtils.copyProperties(horse, Dog.class);
        System.out.println(dog);
    }

    private static void copyList() {
        System.out.println("+++++++++++++++ Original +++++++++++++");
        List<Horse> horses = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Horse horse = MockUtils.createHorse();
            horse.setId((long) i);
            horses.add(horse);
        }
        print(horses);

        System.out.println("+++++++++++++++ Copy All +++++++++++++");
        List<Horse> newHorses = BeanUtils.copyProperties(horses, Horse.class);
        print(newHorses);

        System.out.println("+++++++++++++++ Copy Skip +++++++++++++");
        CopyBeanOptions options = CopyBeanOptions.defaultOptions();
        options.sourceIgnores("speed", "price");
        newHorses = BeanUtils.copyProperties(horses, Horse.class, options);
        print(newHorses);

        System.out.println("+++++++++++++++ Copy Reset +++++++++++++");
        options = CopyBeanOptions.defaultOptions();
        options.targetIgnores("id");
        newHorses = BeanUtils.copyProperties(horses, Horse.class, options);
        print(newHorses);
    }

    private static void print(List<Horse> items) {
        if (items == null) {
            return;
        }
        for (Horse horse : items) {
            System.out.println(horse);
        }
    }
}
