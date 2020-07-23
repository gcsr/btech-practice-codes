
public class Product implements Comparable{
	
	private int id;
	private double fitness;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fitness);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Double.doubleToLongBits(fitness) != Double
				.doubleToLongBits(other.fitness))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Product temp = (Product)arg0;
		if(temp.fitness - this.fitness > 0)			
			return 3;
		else
			return -3;
	}
	
	

}
