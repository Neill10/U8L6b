public class Tester {
    public static void main(String[] args) {
        System.out.println("\n---- TESTING ----");
        Encryptor encryptorTest = new Encryptor(3, 4);
        String actualEncrypted1 = encryptorTest.encryptMessage("What are you doing next weekend?");
        String expectedEncrypted1 = "W  hayaroteu nedgxo tin weAenAedAk?A";
        if (actualEncrypted1.equals(expectedEncrypted1))
        {
            System.out.println("\nTest 1 PASSED!");
        }
        else
        {
            System.out.println("\n Test 1 FAILED! ");
            System.out.println("EXPECTED: " + expectedEncrypted1);
            System.out.println("  ACTUAL: " + actualEncrypted1);
        }
    }
}
