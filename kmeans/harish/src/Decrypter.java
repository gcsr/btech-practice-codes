import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

// class to handle decryption
class FileDecrypter {

	// variable to handle file input
	BufferedReader br;

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to store the line from CardFile
	String line;
	String content;

	// variable to store the prime values used for encryption
	long p;
	long q;
	long n;
	long z;
	long e;
	long d;

	// variable to hold the plain text
	char plainText[] = new char[200];

	// variable to hold the cipher text
	char cipherText[] = new char[200];

	// variable to store the no of characters in the file
	int noOfChar;

	// method to handle file decryption
	void decrypt(String inputFileName) {

		String outputFileName = "D:/AndroidWorkspace/SystemSide/Decrypter/Decrypted/"
				+ inputFileName;
		inputFileName = "D:/AndroidWorkspace/SystemSide/Decrypter/"
				+ inputFileName;

		try {
			br = new BufferedReader(new FileReader(inputFileName));
			content = br.readLine();
			noOfChar = content.length();
			line = content.substring(50, noOfChar - 50);
			noOfChar = line.length();
			br.close();
		} catch (Exception e) {
			System.out.print("FileDecrypter.decrypt:" + outputFileName
					+ " could not be read");
		}

		p = 17;
		q = 19;
		n = p * q;
		z = (p - 1) * (q - 1);

		for (e = 2; e <= z; e++)
			if (gcd(e, z) == 1)
				break;

		for (d = 2; d <= z; d++)
			if ((e * d) % z == 1)
				break;

		for (int i = 0; i < noOfChar; i++) {
			cipherText[i] = line.charAt(i);
			map(cipherText[i], i);
		}

		for (int i = 0; i < noOfChar; i++) {
			if (cipherText[i] != '8' && cipherText[i] != ' '
					&& cipherText[i] != '#' && cipherText[i] != '%'
					&& cipherText[i] != '\'' && cipherText[i] != '('
					&& cipherText[i] != '+' && cipherText[i] != ','
					&& cipherText[i] != '-' && cipherText[i] != '.'
					&& cipherText[i] != '3' && cipherText[i] != '4'
					&& cipherText[i] != '6' && cipherText[i] != '7')
				plainText[i] = (char) power(cipherText[i], d, n);
		}

		try {
			File f = new File(outputFileName);
			f.createNewFile();
			fout = new FileOutputStream(outputFileName);
			pout = new PrintStream(fout);
			pout.print(plainText);
		} catch (Exception e) {
			System.out.print("FileDecrypter.decrypt:" + outputFileName
					+ " could not be written");
		}

	}

	// method to map untraceable cipher text characters
	void map(char c, int i) {

		switch (c) {

		case '8':
			plainText[i] = 'B';
			break;
		case ' ':
			plainText[i] = 'C';
			break;
		case '#':
			plainText[i] = 'O';
			break;
		case '%':
			plainText[i] = 'V';
			break;
		case '\'':
			plainText[i] = 'X';
			break;
		case '(':
			plainText[i] = 'b';
			break;
		case '+':
			plainText[i] = 'c';
			break;
		case ',':
			plainText[i] = 'e';
			break;
		case '-':
			plainText[i] = 'j';
			break;
		case '.':
			plainText[i] = 'q';
			break;
		case '3':
			plainText[i] = 'x';
			break;
		case '4':
			plainText[i] = '5';
			break;
		case '6':
			plainText[i] = '7';
			break;
		case '7':
			plainText[i] = '8';
			break;

		}
	}

	// method to calculate GCD
	long gcd(long number1, long number2) {

		long temp = 1;

		if (number1 > number2) {
			temp = number1;
			number1 = number2;
			number2 = temp;
		}

		while (number1 != 0) {
			temp = number1;
			number1 = number2 % number1;
			number2 = temp;
		}

		return temp;
	}

	// method to calculate power of a number
	long power(long base, long exponent, long n) {

		long factor = 1;

		for (int i = 1; i <= exponent; i++) {
			factor *= base;
			factor %= n;
		}
		return factor;
	}
}

public class Decrypter {
	public static void main(String[] args) {

		FileDecrypter file = new FileDecrypter();
		file.decrypt("CardFile.txt");
	}

}
