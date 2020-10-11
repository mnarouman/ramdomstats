package my.toolkit.randomstats.domain;

import java.util.List;

/**
 * Least Frequent Numbers and stars
 * @author michel
 *
 */
public class LeastFrequentNumbers {
	private List<Integer>numbers ;
	private List<Integer>stars;
	public List<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	public List<Integer> getStars() {
		return stars;
	}
	public void setStars(List<Integer> stars) {
		this.stars = stars;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numbers == null) ? 0 : numbers.hashCode());
		result = prime * result + ((stars == null) ? 0 : stars.hashCode());
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
		LeastFrequentNumbers other = (LeastFrequentNumbers) obj;
		if (numbers == null) {
			if (other.numbers != null)
				return false;
		} else if (!numbers.equals(other.numbers))
			return false;
		if (stars == null) {
			if (other.stars != null)
				return false;
		} else if (!stars.equals(other.stars))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LeastFrequentNumbers [numbers=" + numbers + ", stars=" + stars + "]";
	} 
}
