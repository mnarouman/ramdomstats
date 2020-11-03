package my.toolkit.randomstats.domain;

public class EuroMillionsStatsBean {
	public EuroMillionsStatsBean(int number, long lastWinningDraw, float winningDrawAverage, float outputFrequency,
			long numberOfTimesWinning, int maxBeforeWinningDraw, int numberOfTimesWinningOn10Draws,
			int numberOfTimesWinningOn30Draws, boolean isStar) {
		super();
		this.number = number;
		this.lastWinningDraw = lastWinningDraw;
		this.winningDrawAverage = winningDrawAverage;
		this.outputFrequency = outputFrequency;
		this.numberOfTimesWinning = numberOfTimesWinning;
		this.maxBeforeWinningDraw = maxBeforeWinningDraw;
		this.numberOfTimesWinningOn10Draws = numberOfTimesWinningOn10Draws;
		this.numberOfTimesWinningOn30Draws = numberOfTimesWinningOn30Draws;
		this.isStar = isStar;
	}
	private int number;
	private long lastWinningDraw;
	private float winningDrawAverage;
	private float outputFrequency;
	private long numberOfTimesWinning;
	private int maxBeforeWinningDraw;
	private int numberOfTimesWinningOn10Draws;
	private int numberOfTimesWinningOn30Draws;
	private boolean isStar = false;
	public int getNumber() {
		return number;
	}
	public long getLastWinningDraw() {
		return lastWinningDraw;
	}
	public float getWinningDrawAverage() {
		return winningDrawAverage;
	}
	public float getOutputFrequency() {
		return outputFrequency;
	}
	public long getNumberOfTimesWinning() {
		return numberOfTimesWinning;
	}
	public int getMaxBeforeWinningDraw() {
		return maxBeforeWinningDraw;
	}
	public int getNumberOfTimesWinningOn10Draws() {
		return numberOfTimesWinningOn10Draws;
	}
	public int getNumberOfTimesWinningOn30Draws() {
		return numberOfTimesWinningOn30Draws;
	}
	public boolean isStar() {
		return isStar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isStar ? 1231 : 1237);
		result = prime * result + (int) (lastWinningDraw ^ (lastWinningDraw >>> 32));
		result = prime * result + maxBeforeWinningDraw;
		result = prime * result + number;
		result = prime * result + (int) (numberOfTimesWinning ^ (numberOfTimesWinning >>> 32));
		result = prime * result + numberOfTimesWinningOn10Draws;
		result = prime * result + numberOfTimesWinningOn30Draws;
		result = prime * result + Float.floatToIntBits(outputFrequency);
		result = prime * result + Float.floatToIntBits(winningDrawAverage);
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
		EuroMillionsStatsBean other = (EuroMillionsStatsBean) obj;
		if (isStar != other.isStar)
			return false;
		if (lastWinningDraw != other.lastWinningDraw)
			return false;
		if (maxBeforeWinningDraw != other.maxBeforeWinningDraw)
			return false;
		if (number != other.number)
			return false;
		if (numberOfTimesWinning != other.numberOfTimesWinning)
			return false;
		if (numberOfTimesWinningOn10Draws != other.numberOfTimesWinningOn10Draws)
			return false;
		if (numberOfTimesWinningOn30Draws != other.numberOfTimesWinningOn30Draws)
			return false;
		if (Float.floatToIntBits(outputFrequency) != Float.floatToIntBits(other.outputFrequency))
			return false;
		if (Float.floatToIntBits(winningDrawAverage) != Float.floatToIntBits(other.winningDrawAverage))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EuroMillionsStatsBean [number=" + number + ", lastWinningDraw=" + lastWinningDraw
				+ ", winningDrawAverage=" + winningDrawAverage + ", outputFrequency=" + outputFrequency
				+ ", numberOfTimesWinning=" + numberOfTimesWinning + ", maxBeforeWinningDraw=" + maxBeforeWinningDraw
				+ ", numberOfTimesWinningOn10Draws=" + numberOfTimesWinningOn10Draws
				+ ", numberOfTimesWinningOn30Draws=" + numberOfTimesWinningOn30Draws + ", isStar=" + isStar + "]";
	}
}
