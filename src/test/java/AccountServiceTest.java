import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import longtp.example.AccountService;

public class AccountServiceTest {
    private static final String INPUT_FILE = "src/data.csv";
    private static final String OUTPUT_FILE = "src/UnitTest.csv";
    private static AccountService service;

    @BeforeAll
    public static void setup() {
        service = new AccountService();
    }

    @Test
    public void testRegisterAccountsFromCSV() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.csv");
        assertNotNull(inputStream, "Không tìm thấy file data.csv trong resources.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/UnitTest.csv")); // vẫn có thể ghi ra đây

        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                writer.write(line + ",actual_result\n");
                firstLine = false;
                continue;
            }

            String[] parts = line.split(",", -1);
            String username = parts[0].trim();
            String password = parts[1].trim();
            String email = parts[2].trim();
            boolean expected = Boolean.parseBoolean(parts[3].trim());

            boolean actual = service.registerAccount(username, password, email);
            boolean result = (actual == expected);

            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    username, password, email, expected, actual, result ? "PASS" : "FAIL"));

            assertEquals(expected, actual, "Test failed for: " + username);
        }

        reader.close();
        writer.close();
    }
}

