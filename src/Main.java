public class Main {
    private static final String BARCODE = "123456789101"; // EAN-13: CC OOOOO AAAAA D with C:country, O:organisation, A:article, D:digit
    private static int[] barcodeInt;

    public static void main(String[] args) {
        barcodeInt = convertToIntArray();
        checkDigit();
    }

    /**
     * This function takes the barcode string and extracts every number into an array
     *
     * @return The converted Integer array
     */
    private static int[] convertToIntArray() {
        int[] barcodeInt = new int[Main.BARCODE.length() + 1];
        for (int i = 0; i < Main.BARCODE.length(); i++) {
            barcodeInt[i] = Integer.parseInt(Main.BARCODE.charAt(i) + "");
        }
        return barcodeInt;
    }

    /**
     * Check whether the check digit is correct or (if none existing) calculate one
     */
    private static void checkDigit() {
        int checkSum = calcDigit();
        if (BARCODE.length() == 13) {
            if (checkSum == barcodeInt[12]) {
                System.out.println("This barcode (" + BARCODE + ") is a valid barcode!");
            } else {
                System.out.println("This barcode (" + BARCODE + ") is invalid! (Correct digit: " + checkSum + ")");
            }
        } else if (BARCODE.length() == 12) {
            System.out.println("The correct digit for this barcode (" + BARCODE + checkSum + ") is " + checkSum);
        }
        // visualizeBinCode();
    }

    /**
     * This function calculates the check digit for the specific barcode
     *
     * @return The check digit
     */
    private static int calcDigit() {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += barcodeInt[i]; // if even, add the integer once
            if (i % 2 == 1) {
                sum += 2 * barcodeInt[i]; // if uneven, add the integer three times, two times more
            }
        }
        int digit = 10 - sum % 10; // calculate the difference
        if (digit == 10) digit = 0;
        return digit;
    }

    /**
     * This function gets the code via String and formats it for correct output.
     */
    private static void visualizeBinCode() {
        String visualization = "";
        for (int i = 0; i < barcodeInt.length; i++) {
            if (barcodeInt[i] == 0) {
                visualization += " ";
            } else {
                visualization += "|";
            }
        }
        System.out.println(visualization);
    }
}
