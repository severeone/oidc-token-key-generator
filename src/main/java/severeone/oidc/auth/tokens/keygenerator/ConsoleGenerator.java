package severeone.oidc.auth.tokens.keygenerator;

import severeone.oidc.auth.tokens.gen.AccessTokenKeyGenerator;
import severeone.oidc.auth.tokens.gen.IDTokenKeyGenerator;

import com.nimbusds.jose.JOSEException;

import java.security.NoSuchAlgorithmException;

public class ConsoleGenerator {

    private static final String CHECK = "check";
    private static final String ID = "id";
    private static final String ACCESS = "access";

    public static void main(String[] args) throws NoSuchAlgorithmException, JOSEException {
        if (args.length == 0) {
            System.out.printf("Usage: access-token-key-generator %s|%s [%s <key> [<key>...]]", ID, ACCESS, CHECK);
        } else if (args.length == 1) {
            if (ACCESS.equals(args[0])) {
                System.out.println(AccessTokenKeyGenerator.generate());
            } else if (ID.equals(args[0])) {
                System.out.println(IDTokenKeyGenerator.generate());
            }
        } else {
            if (CHECK.equals(args[1])) {
                for (int i = 2; i < args.length; ++i) {
                    String key = args[i];
                    if (ACCESS.equals(args[0])) {
                        System.out.println(AccessTokenKeyGenerator.checkFormat(key) ? "valid" : "invalid");
                    } else if (ID.equals(args[0])) {
                        System.out.println(IDTokenKeyGenerator.parse(key).toString());
                    } else {
                        System.out.println("unknown token key type");
                    }
                }
            }
        }
    }
}
