import java.util.Scanner;


public class Multiplication_Strassen {
	public static void main(String[] gcs){
		Multiplication_Strassen ms=new Multiplication_Strassen();
		if(!ms.createMatrices()){
			System.out.println("matrix sizes should be powers of 2");
			System.out.println("closing program");
			return;
		}
		double[][] result=ms.multiply(ms.getMul1(), ms.getMul2());
		System.out.println("Statistics of product");
		int size=ms.getMul1().length;
		
		System.out.println("Total number of multiplications are : " +ms.getMultiplications());
		System.out.println("Total number of additions are : " +ms.getAdditions());
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.print(result[i][j] + "\t");
			System.out.println();
		}
	
	}
	
	double[][] mul1;
	double[][] mul2;
	
	int additions;
	int multiplications;
	
	
	
	/** function to get number of additons
	 * 
	 * @return number
	 */
	public int getAdditions() {
		return additions;
	}


	/** function to set number of additons
	 * 
	 * @return number
	 */
	public void setAdditions(int additions) {
		this.additions = additions;
	}

	/** function to get number of multiplications
	 * 
	 * @return number
	 */
	public int getMultiplications() {
		return multiplications;
	}

	/** function to set number of multiplications
	 * 
	 * @return number
	 */
	public void setMultiplications(int multiplications) {
		this.multiplications = multiplications;
	}

	/** function to get matrix1
	 * 
	 * @return matrix1
	 */
	public double[][] getMul1(){
		return mul1;
	}
	
	/** function to get matrix 2
	 * 
	 * @return matrix2
	 */
	public double[][] getMul2(){
		return mul2;
	}
	
	/** constructor
	 * 
	 */
	public Multiplication_Strassen(){
		additions=0;
		multiplications=0;
	}
	
	
	/** function for creating matrices
	 * 
	 */
	public boolean createMatrices(){
		Scanner scanner=new Scanner(System.in);;
		System.out.println("Enter size of matrices involved in multiplication");
		int size=scanner.nextInt();
		boolean rrr=false;
		for(int i=2;i<=size;i*=2){
			if(i==size)
				rrr=true;
		}
		
		if(rrr==false)
			return false;
		
		mul1=new double[size][size];
		mul2=new double[size][size];		
		
		System.out.println("Enter matrix 1");
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				mul1[i][j]=scanner.nextDouble();
			}
		}
		
		System.out.println("Enter matrix 2");
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				mul2[i][j]=scanner.nextDouble();
			}
		}
		
		
		return true;
	}
	
	/** function for getting multiplication
	 * 
	 * @param mul1 matrix1 in multiplication
	 * @param mul2 matrix2 in multiplication
	 * @return result;
	 */
	public double[][] multiply(double[][] mul1,double[][] mul2){
		int size = mul1.length;
		double result[][]=new double[size][size];		

		if (size == 1){
			result[0][0] = mul1[0][0] * mul2[0][0];
			//multiplications++;
		}
		else {
			int blockSize=size/2;
			double[][] A11 = new double[blockSize][blockSize];
			double[][] A12 = new double[blockSize][blockSize];
			double[][] A21 = new double[blockSize][blockSize];
			double[][] A22 = new double[blockSize][blockSize];
			double[][] B11 = new double[blockSize][blockSize];
			double[][] B12 = new double[blockSize][blockSize];
			double[][] B21 = new double[blockSize][blockSize];
			double[][] B22 = new double[blockSize][blockSize];

			/** from wikpedia 
			 * Dividing matrix A into 4 halves **/
			A11=split(mul1,blockSize, 0, 0);
			A12=split(mul1, blockSize, 0, blockSize);
			A21=split(mul1, blockSize, blockSize, 0);
			A22=split(mul1, blockSize, blockSize, blockSize);
			/**from wikipedia
			 *  Dividing matrix B into 4 halves **/
			B11=split(mul2, blockSize, 0, 0);
			B12=split(mul2, blockSize, 0, blockSize);
			B21=split(mul2, blockSize, blockSize, 0);
			B22=split(mul2, blockSize, blockSize, blockSize);

			/** from wikipedia
			 * M1 = (A11 + A22)*(B11 + B22) 
			 * M2 = (A21 + A22) * B11 
			 * M3 = A11 * (B12 - B22)
			 * M4 = A22 *(B21 - B11)
			 * M5 = (A11 + A12)* B22 
			 * M6 = (A21 - A11)* (B11 + B12) 
			 * M7 = (A12 - A22)*(B21 + B22)
			 **/

			double[][] M1 = multiply(add(A11, A22), add(B11, B22));
			double[][] M2 = multiply(add(A21, A22), B11);
			double[][] M3 = multiply(A11, sub(B12, B22));
			double[][] M4 = multiply(A22, sub(B21, B11));
			double[][] M5 = multiply(add(A11, A12), B22);
			double[][] M6 = multiply(sub(A21, A11), add(B11, B12));
			double[][] M7 = multiply(sub(A12, A22), add(B21, B22));
			
			multiplications=multiplications+7;//Since above 7 multiplications are performed 

			/**from wikipedia
			 * C11 = M1 + M4 - M5 + M7 C12 = M3 + M5 C21 = M2 + M4 C22 = M1 - M2
			 * + M3 + M6
			 **/
			double[][] C11 = add(sub(add(M1, M4), M5), M7);
			double[][] C12 = add(M3, M5);
			double[][] C21 = add(M2, M4);
			double[][] C22 = add(sub(add(M1, M3), M2), M6);

			
			join(C11,result, 0, 0);
			join(C12,result, 0, blockSize);
			join(C21,result,blockSize, 0);
			join(C22,result,blockSize, blockSize);
			
		}
		return result;

	}
		
		/** Function for getting subtraction
		 * 
		 * @param temp1 matrix 1 for subtraction
		 * @param temp2 matrix2 for subtraction
		 * @return subtracted matrix
		 */
		public double[][] sub(double[][] temp1, double[][] temp2) {
			int temp1Length= temp1.length;
			double[][] result=new double[temp1Length][temp1Length];
			
			for (int i = 0; i < temp1Length; i++)
				for (int j = 0; j < temp1Length; j++){
					result[i][j] = temp1[i][j] - temp2[i][j];
					additions++;
				}
			return result;
		}

		/** Function for getting addition
		 * 
		 * @param temp1 matrix1 for addition
		 * @param temp2 matrix2 for addition
		 * @return added matrix
		 */
		public double[][] add(double[][] temp1, double[][] temp2) {
			int temp1Length = temp1.length;
			double[][] result = new double[temp1Length][temp1Length];
			for (int i = 0; i < temp1Length; i++)
				for (int j = 0; j < temp1Length; j++){
					result[i][j] = temp1[i][j] + temp2[i][j];
					additions++;
				}
			return result;
		}

		/** Function for getting splitting
		 * 
		 * @param temp main matrix
		 * @param blockSize size of split matrix
		 * @param end1 split1 dimension
		 * @param end2 split2 dimension
		 * @return
		 */
		public double[][] split(double[][] temp, int blockSize, int end1, int end2) {
			double[][] result=new double[blockSize][blockSize];
			for (int i = 0, i2 = end1; i < blockSize; i++, i2++)
				for (int j = 0, j2 = end2; j < blockSize; j++, j2++)
					result[i][j] = temp[i2][j2];
			return result;
		}

		/** Function to join
		 * 
		 * @param temp matrix to be joined
		 * @param result result after joining
		 * @param end1 joining dimension1
		 * @param end2 joining dimension2
		 */
		
		public void join(double[][] temp, double[][] result,int end1, int end2) {
			
			int size=temp.length;
			for (int i = 0, i2 = end1; i < size; i++, i2++)
				for (int j = 0, j2 = end2; j < size; j++, j2++)
					result[i2][j2] = temp[i][j];
			
		}
	
	
}
