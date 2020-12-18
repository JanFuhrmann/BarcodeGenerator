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
        visualizeBinCode();
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
     * Calculate the binary code for the known barcode
     *
     * @return The binary string
     */
    private static String calcBin() {
        StringBuilder fullCodeText = new StringBuilder("101");
        boolean drawMiddleLine = true;
        for (int i = 1; i < barcodeInt.length; i++) {
            if (i < 7) {
                if (barcodeInt[i] == 0) {
                    fullCodeText.append("0001101");
                } else if (barcodeInt[i] == 1) {
                    fullCodeText.append("0011001");
                } else if (barcodeInt[i] == 2) {
                    fullCodeText.append("0010011");
                } else if (barcodeInt[i] == 3) {
                    fullCodeText.append("0111101");
                } else if (barcodeInt[i] == 4) {
                    fullCodeText.append("0100011");
                } else if (barcodeInt[i] == 5) {
                    fullCodeText.append("0110001");
                } else if (barcodeInt[i] == 6) {
                    fullCodeText.append("0101111");
                } else if (barcodeInt[i] == 7) {
                    fullCodeText.append("0111011");
                } else if (barcodeInt[i] == 8) {
                    fullCodeText.append("0110111");
                } else if (barcodeInt[i] == 9) {
                    fullCodeText.append("0001011");
                }
            } else {
                if (drawMiddleLine) {
                    fullCodeText.append("01010");
                    drawMiddleLine = false;
                }
                if (barcodeInt[i] == 0) {
                    fullCodeText.append("1110010");
                } else if (barcodeInt[i] == 1) {
                    fullCodeText.append("1100110");
                } else if (barcodeInt[i] == 2) {
                    fullCodeText.append("1101100");
                } else if (barcodeInt[i] == 3) {
                    fullCodeText.append("1000010");
                } else if (barcodeInt[i] == 4) {
                    fullCodeText.append("1011100");
                } else if (barcodeInt[i] == 5) {
                    fullCodeText.append("1001110");
                } else if (barcodeInt[i] == 6) {
                    fullCodeText.append("1010000");
                } else if (barcodeInt[i] == 7) {
                    fullCodeText.append("1000100");
                } else if (barcodeInt[i] == 8) {
                    fullCodeText.append("1001000");
                } else if (barcodeInt[i] == 9) {
                    fullCodeText.append("1110100");
                }
            }
        }
        fullCodeText.append("101");
        return fullCodeText.toString();
    }

    /**
     * This function gets the code via String and formats it for correct output.
     */
    private static void visualizeBinCode() {
        String code = calcBin();
        StringBuilder visualization = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                visualization.append(" ");
            } else {
                visualization.append("|");
            }
        }
        System.out.println(code);
        System.out.println(visualization);
    }
}
