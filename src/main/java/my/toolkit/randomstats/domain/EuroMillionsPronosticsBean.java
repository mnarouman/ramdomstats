package my.toolkit.randomstats.domain;

import java.util.List;

import lombok.Data;

@Data
public class EuroMillionsPronosticsBean {
	private List<EuroMillionsStatsBean> allStats;
	private LeastFrequentNumbers leastFrequentNumbers;
	private LessAnticipatedNumbers lessAnticipatedNumbers;
	private MostAnticipatedNumbers mostAnticipatedNumbers;
	private int numberOfDraws;
	
	public EuroMillionsPronosticsBean(List<EuroMillionsBean> euroMillionsBeans) {
		this.numberOfDraws =  euroMillionsBeans.size();
	}
	
	
	@Override
	public String toString() {
		StringBuffer pronostics = new StringBuffer();
		pronostics.append("***** NUMBERS STATS FOR " + numberOfDraws + " DRAWS ******\n");
		pronostics.append("|Number\t"
				+ "|Is Star\t"
				+ "|last Winning Draw\t"
				+ "|winning Draw Average\t"
				+ "|Times Winning\t"
				+ "|Output Frequency\t"
				+ "|Max Before Winning Draw\t"
				+ "|Winning On last 10 Draws\t"
				+ "|Winning On last 30 Draws\t"
				+ "|\n");
		for (EuroMillionsStatsBean stats : allStats) {
			int number = stats.getNumber();
			String star = stats.isStar()?"yes":"no";
			long actualDeviation = stats.getLastWinningDraw();
			float ponctualDeviation = stats.getWinningDrawAverage();
			long outputWinner = stats.getNumberOfTimesWinning();
			float outputFrequency = stats.getOutputFrequency();
			int maxDeviation = stats.getMaxBeforeWinningDraw();
			int recentForm = stats.getNumberOfTimesWinningOn10Draws();
			int generalForm = stats.getNumberOfTimesWinningOn30Draws();
			pronostics.append("|"+number+"\t"+
					"|"+star+"\t\t"+
					"|"+actualDeviation+"\t\t\t"+
					"|"+ponctualDeviation+"\t\t\t"+
					"|"+outputWinner+"\t\t"+
					"|"+outputFrequency+"\t\t"+
					"|"+maxDeviation+"\t\t\t\t"+
					"|"+recentForm+"\t\t\t\t"+
					"|"+generalForm+"\t\t\t\t"+
					"|"+"\n");
		}
		pronostics.append("\n");
		
		return pronostics.toString();
	}

	

	
}
