import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Random;

// class to handle encryption
class FileEncrypter {

	// variable to handle file input
	BufferedReader br;

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to store the contents of the input file
	String line;

	// variable to store the values used for encryption
	long p;
	long q;
	long n;
	long z;
	long e;
	long d;

	// variable to hold the plain text
	char plainText[] = new char[100];

	// variable to hold the cipher text
	char cipherText[] = new char[100];
	String cipherString = "";

	// variable to store the no of characters in the file
	int noOfChar;

	// variable to handle random padding
	Random r = new Random();

	// method to handle file encryption
	void encrypt(String inputFileName) {

		String outputFileName = "D:/AndroidWorkspace/SystemSide/Encrypter/Encrypted/"
				+ inputFileName;
		inputFileName = "D:/AndroidWorkspace/SystemSide/Encrypter/"
				+ inputFileName;

		try {
			br = new BufferedReader(new FileReader(inputFileName));

			line = br.readLine();
			noOfChar = line.length();
			br.close();
		} catch (Exception e) {
			System.out.print("FileEncrypter.encrypt:" + outputFileName
					+ " could not be read");
		}

		p = 17;
		q = 19;
		n = p * q;
		z = (p - 1) * (q - 1);

		for (e = 2; e <= z; e++)
			if (gcd(e, z) == 1)
				break;

		for (int i = 0; i < noOfChar; i++) {
			plainText[i] = line.charAt(i);
			map(plainText[i], i);
		}

		for (int i = 0; i < noOfChar; i++)
			if (plainText[i] != 'B' && plainText[i] != 'C'
					&& plainText[i] != 'O' && plainText[i] != 'V'
					&& plainText[i] != 'X' && plainText[i] != 'b'
					&& plainText[i] != 'c' && plainText[i] != 'e'
					&& plainText[i] != 'j' && plainText[i] != 'q'
					&& plainText[i] != 'x' && plainText[i] != '5'
					&& plainText[i] != '7' && plainText[i] != '8')
				cipherText[i] = (char) (power(plainText[i], e, n));

		try {
			File f = new File(outputFileName);
			f.createNewFile();
			fout = new FileOutputStream(outputFileName);
			pout = new PrintStream(fout);
			for (int i = 0; i < noOfChar; i++)
				cipherString += cipherText[i];
			for (int i = 0; i < 50; i++) {
				char randomChar = (char) (r.nextInt(127) + 33);
				pout.print(randomChar);
			}
			pout.print(cipherString);
			for (int i = 0; i < 50; i++) {
				char randomChar = (char) (r.nextInt(95) + 33);
				pout.print(randomChar);
			}
		} catch (Exception e) {
			System.out.print("FileEncrypter.encrypt:" + outputFileName
					+ " could not be written");
		}

	}

	// method to map untraceable plain text characters
	void map(char c, int i) {

		switch (c) {

		case 'B':
			cipherText[i] = '8';
			break;
		case 'C':
			cipherText[i] = ' ';
			break;
		case 'O':
			cipherText[i] = '#';
			break;
		case 'V':
			cipherText[i] = '%';
			break;
		case 'X':
			cipherText[i] = '\'';
			break;
		case 'b':	
			cipherText[i] = '(';
			break;
		case 'c':
			cipherText[i] = '+';
			break;
		case 'e':
			cipherText[i] = ',';
			break;
		case 'j':
			cipherText[i] = '-';
			break;
		case 'q':
			cipherText[i] = '.';
			break;
		case 'x':
			cipherText[i] = '3';
			break;
		case '5':
			cipherText[i] = '4';
			break;
		case '7':
			cipherText[i] = '6';
			break;
		case '8':
			cipherText[i] = '7';
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
	long power(char base, long exponent, long n) {

		long factor = 1;

		for (int i = 1; i <= exponent; i++) {
			factor *= base;
			factor %= n;
		}

		return factor;
	}

}

public class Encrypter {

	public static void main(String[] args) {

		FileEncrypter file = new FileEncrypter();
		file.encrypt("CardFile.txt");
	}

}


 