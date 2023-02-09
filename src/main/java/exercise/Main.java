package exercise;

import java.util.*;
import java.math.BigDecimal;

/*
  TASK:  Fill in the mapToOutput method to take the input map below and create a more structure
  output object.  Some string manipulation and basic math will be needed.

  INPUT amount is listed in pennies and need to be converted into decimal.
    i.e. "1001" should be converted to "10.01"

  INPUT:
  {
    "accountId": "W1234",
    "transaction": {
        "amount": "USD|99999"
    },
    "references": [
      "DATA:MATT",
      "DATA:BLAKE",
      "DATA:JASON"
    ]
  }

  Expected:
  {
    "accountId": "W1234",
    "amount": "999.99" (Big Decimal)
    "currency": "USD",
    "references": [
      MATT, BLAKE, UNKNOWN
    ]
  }

*/

class Main {

    private final static Map<String, Object> INPUT = Main.getInput();
    private final static Output EXPECTED = Main.getExpectedOutput();

    private enum Reference {
        MATT, BLAKE, UNKNOWN
    }

    private static final class Output {
        public BigDecimal amount;
        public String currency;
        public String accountId;
        public List<Reference> references;
    }

    private static Output mapToOutput(Map<String, Object> input) {
        Output output = new Output();

        Map<String, String> inputTransaction = (Map<String, String>) input.get("transaction");

        /*
        Start your code here
         */

        return output;
    }

    public static void main(String[] args) {

        try {
            System.out.println("Running mapper function...");
            Output created = Main.mapToOutput(Main.INPUT);
            System.out.println("Checking match...");
            Main.checkMatches(created, Main.EXPECTED);

            System.out.println("Matched!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed Match.");
        }

    }

    private static void checkMatches(Output created, Output expected) {
        if (created == null) {
            throw new RuntimeException("created was null!");
        }
        if (!expected.accountId.equals(created.accountId)) {
            throw new RuntimeException("accountId didn't match");
        }
        if (!expected.currency.equals(created.currency)) {
            throw new RuntimeException("currency didn't match");
        }
        if (!expected.amount.equals(created.amount)) {
            throw new RuntimeException("amount didn't match");
        }
        if (expected.references.size() != created.references.size()) {
            throw new RuntimeException("Wrong number of references");
        }
        for (int i = 0; i < expected.references.size(); i++) {
            if (expected.references.get(i) != created.references.get(i)) {
                throw new RuntimeException("References were wrong at index: " + i);
            }
        }
    }

    private static Map<String, Object> getInput() {
        Map<String, Object> input = new HashMap<>();
        input.put("accountId", "W1234");
        Map<String, String> subField = new HashMap<>();
        subField.put("amount", "USD|99999");
        input.put("transaction", subField);
        List<String> references = List.of("DATA:MATT", "DATA:BLAKE", "DATA:JASON");
        input.put("references", references);
        return input;
    }

    private static Output getExpectedOutput() {
        Output expected = new Output();
        expected.amount = BigDecimal.valueOf(999.99D);
        expected.currency = "USD";
        expected.accountId = "W1234";
        expected.references = List.of(Reference.MATT, Reference.BLAKE, Reference.UNKNOWN);
        return expected;
    }

}
