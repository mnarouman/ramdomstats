package my.toolkit.randomstats.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class EuroMillionsStatsBean {
	private int number;
	private long lastWinningDraw;
	private float winningDrawAverage;
	private float outputFrequency;
	private long numberOfTimesWinning;
	private int maxBeforeWinningDraw;
	private int numberOfTimesWinningOn10Draws;
	private int numberOfTimesWinningOn30Draws;
	private boolean isStar = false;
}
