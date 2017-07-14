import javax.security.auth.callback.Callback;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Vaisiliy Es'kin on 07/09/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println((-(byte)128)>>>1);
        int i = 0;
        i += i++;
        System.out.println(i);

        int z = 1;
        z += z += z += z += z;
        System.out.println(z);

        for (int j = 0; j < 2; j++) {
            System.out.println(j);
        }

        Double x = 4.0;
        trippleValue(x);
        System.out.println(x);

    }

    public <T extends String> void gg(){return;}

    public static void trippleValue(Double x) {
        x = x * 3.0;
    }

    enum EnumTest {
        VALUE1 {
            public EnumTest getValue() {
                return VALUE2.getValue().getValue();
            }
        },

        VALUE2 {
            public EnumTest getValue() {
                return VALUE1;
            }
        };

        abstract EnumTest getValue();
    }

}
