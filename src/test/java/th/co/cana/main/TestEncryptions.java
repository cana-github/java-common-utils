package th.co.cana.main;

import th.co.cana.framework.utils.Encryptions;
import th.co.cana.framework.utils.exception.SystemException;

/**
 * @author supot.jdev
 * @version 1.0
 */
public class TestEncryptions {
    public static void main(String[] args) throws SystemException {
        String data = "Mr. Supot Saelao";
        System.out.println(data);
        data = Encryptions.encryptAES(data);
        System.out.println(data);
    }
}
