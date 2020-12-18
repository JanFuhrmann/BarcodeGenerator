public class Main {

    public static void main(String[] args) {
	String b = "010101010101";
	int[] a = speichereArray(b);
	if (b.length() == 12) {
	    int pz = berechnePruefziffer(a);
	    System.out.println("Prüfziffer: " + pz);
	    a[12] = pz;
	}
	String code = berechneCode(a);
	String vis = visualisiere(code);
	System.out.println(code);
	System.out.println(vis);
    }

    private static String visualisiere(String berechneCode) {
	String vis = "";
	for (int i = 0; i < berechneCode.length(); i++) {
	    if (berechneCode.charAt(i) == '0') {
		vis += " ";
	    } else {
		vis += "|";
	    }
	}
	return vis;
    }

    private static String berechneCode(int[] a) {
	String code = "101";
	boolean setzeMitte = true;
	for (int i = 1; i < a.length; i++) {
	    if (i < 7) {
		if (a[i] == 0) {
		    code += "0001101";
		} else if (a[i] == 1) {
		    code += "0011001";
		} else if (a[i] == 2) {
		    code += "0010011";
		} else if (a[i] == 3) {
		    code += "0111101";
		} else if (a[i] == 4) {
		    code += "0100011";
		} else if (a[i] == 5) {
		    code += "0110001";
		} else if (a[i] == 6) {
		    code += "0101111";
		} else if (a[i] == 7) {
		    code += "0111011";
		} else if (a[i] == 8) {
		    code += "0110111";
		} else if (a[i] == 9) {
		    code += "0001011";
		}
	    } else {
		if (setzeMitte) {
		    code += "01010";
		    setzeMitte = false;
		}
		if (a[i] == 0) {
		    code += "1110010";
		} else if (a[i] == 1) {
		    code += "1100110";
		} else if (a[i] == 2) {
		    code += "1101100";
		} else if (a[i] == 3) {
		    code += "1000010";
		} else if (a[i] == 4) {
		    code += "1011100";
		} else if (a[i] == 5) {
		    code += "1001110";
		} else if (a[i] == 6) {
		    code += "1010000";
		} else if (a[i] == 7) {
		    code += "1000100";
		} else if (a[i] == 8) {
		    code += "1001000";
		} else if (a[i] == 9) {
		    code += "1110100";
		}
	    }
	}
	code += "101";
	return code;
    }

    private static int[] speichereArray(String b) {
	int[] a = new int[13];
	for (int i = 0; i < b.length(); i++) {
	    a[i] = Integer.parseInt(b.charAt(i) + "");
	}
	return a;
    }

    private static int berechnePruefziffer(int[] a) {
	int sum = 0;
	for (int i = 0; i < a.length; i++) {
	    if (i % 2 == 0) {
		sum += a[i];
	    } else {
		sum += 3 * a[i];
	    }
	}
	int z = 10 - sum % 10;
	if (z == 10) {
	    z = 0;
	}
	return z;
    }
}
