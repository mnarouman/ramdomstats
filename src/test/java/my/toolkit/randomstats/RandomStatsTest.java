package my.toolkit.randomstats;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.domain.EuroMillionsPronosticsBean;
import my.toolkit.randomstats.domain.EuroMillionsStatsBean;
import my.toolkit.randomstats.services.RandomStatsServices;
import my.toolkit.randomstats.utils.RandomStatsUtils;
@SpringBootTest
public class RandomStatsTest {
	private final static Logger LOG = LoggerFactory.getLogger(RandomStatsTest.class);
	private static final String CSV_EUROMILLIONS_2020_09_18 = "csv/euromillions-2020-09-18.csv";
	private static final String CSV_EUROMILLIONS_2020_09_22 = "csv/euromillions-2020-09-22.csv";
	private static final String CSV_EUROMILLIONS_2020_10_09_SMALL = "csv/euromillions-2020-10-09-small.csv";
	private static final String CSV_EUROMILLIONS_2020_10_09 = "csv/euromillions-2020-10-09.csv";
	private static final String CSV_EUROMILLIONS = "csv/euromillions.csv";
	private List<EuroMillionsBean> beans;
	private List<EuroMillionsBean> beans20200922;
	private List<EuroMillionsBean> beans20201009_small;
	private List<EuroMillionsBean> beans20201009;
	
	private BiPredicate<Integer, EuroMillionsBean> isNumberOut = RandomStatsUtils.getIsNumberOut();
	private BiPredicate<Integer, EuroMillionsBean> isStarOut = RandomStatsUtils.getIsStarOut();
			
	static {
		System.setProperty("java.awt.headless", "false");
	}
	
	@Autowired
	private RandomStatsServices services;
	
	@BeforeEach
	public void init() {
		beans = services.loadBean(CSV_EUROMILLIONS);
		beans20200922 = services.loadBean(CSV_EUROMILLIONS_2020_09_22);
		beans20201009_small = services.loadBean(CSV_EUROMILLIONS_2020_10_09_SMALL);
		beans20201009 = services.loadBean(CSV_EUROMILLIONS_2020_10_09);
		LOG.debug("************ Number of draws :" + beans.size() );

	}
	
	@Test
	public void loadBeans() throws Exception {
		// given
		String csvFileName = CSV_EUROMILLIONS_2020_09_22;

		// when
		List<EuroMillionsBean> csvFile = services.loadBean(csvFileName);

		// then
		Assertions.assertThat(csvFile).isNotNull();
		Assertions.assertThat(csvFile.size()).isEqualTo(1357);
		
	}
	
	/**
	 * number of lottery draws where the number is not out
	 * @throws Exception
	 */
	@Test
	public void lastWinningDrawFor22() throws Exception {
		// given
		int number = 22;
		
		// when
		long actualDeviation = services.lastWinningDraw(beans20200922, number, isNumberOut);

		// then
		Assertions.assertThat(actualDeviation).isEqualTo(5);
		
	}
	
	/**
	 * number of lottery draws where the number is not out
	 * @throws Exception
	 */
	@Test
	public void lastWinningDrawFor19() throws Exception {
		// given
		int number = 19;
		
		// when
		long actualDeviation = services.lastWinningDraw(beans20201009_small, number, isNumberOut);

		// then
		Assertions.assertThat(actualDeviation).isEqualTo(11);
		
	}

	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void winningDrawAverageFor22() throws Exception {
		// given
		int number = 22;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.winningDrawAverage(beans20200922, number, nbDraws, isNumberOut);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(13.30f);
		
	}
	
	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void winningDrawAverageFor23InFirst20() throws Exception {
		// given
		int number = 23;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.winningDrawAverage(beans20201009_small, number, nbDraws, isNumberOut);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(2.50f);
		
	}
	
	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void winningDrawAverageFor15InFirst20() throws Exception {
		// given
		int number = 15;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.winningDrawAverage(beans20201009_small, number, nbDraws, isNumberOut);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(3.00f);
		
	}

	
	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void winningDrawAverageFor23() throws Exception {
		// given
		int number = 23;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.winningDrawAverage(beans20200922, number, nbDraws, isNumberOut);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(6.80f);
		
	}
	
	/**
	 * number of times the number is output
	 * @throws Exception
	 */
	@Test
	public void numberOfTimesWinning() throws Exception {
		// given
		int number = 22;
		
		// when
		long outputWinner = services.numberOfTimesWinning(beans20200922, number, isNumberOut);
		
		// then
		Assertions.assertThat(outputWinner).isEqualTo(116);
		
	}
	
	/**
	 * Output frequency
	 * @throws Exception
	 */
	@Test
	public void outputFrequency() throws Exception {
		// given
		int number = 22;
		
		// when
		float outputFrequency = services.outputFrequency(beans20200922, number, isNumberOut);

		// then
		Assertions.assertThat(String.format("%.2f", outputFrequency)).isEqualTo("0,09");
		
	}
	
	/**
	 * Maximum number of draws during which the number was delayed
	 * @throws Exception
	 */
	@Test
	public void maximumBeforeWinningDrawFor22() throws Exception {
		// given
		int number = 22;
		
		// when
		int maxDeviation = services.maxBeforeWinningDraw(beans20200922, number, isNumberOut);

		// then
		Assertions.assertThat(maxDeviation).isEqualTo(57);
		
	}
	
	/**
	 * Maximum number of draws during which the number was delayed
	 * @throws Exception
	 */
	@Test
	public void maximumBeforeWinningDrawFor15InFirst20() throws Exception {
		// given
		int number = 15;
		
		// when
		int maxDeviation = services.maxBeforeWinningDraw(beans20201009_small, number, isNumberOut);

		// then
		Assertions.assertThat(maxDeviation).isEqualTo(5);
		
	}
	
	@Test
	public void maximumBeforeWinningDrawFor43() throws Exception {
		// given
		int number = 43;
		
		// when
		int maxDeviation = services.maxBeforeWinningDraw(beans20200922, number, isNumberOut);

		// then
		Assertions.assertThat(maxDeviation).isEqualTo(75);
		
	}

	/**
	 * number of times the number appeared on the last 10 draws
	 * @throws Exception
	 */
	@Test
	public void recentAppearances() throws Exception {
		// given
		int number = 22;
		int numberOfDraws = 10;
		
		// when
		int appearances = services.numberOfTimesWinningOnNDraws(beans20200922, number, numberOfDraws, isNumberOut);

		// then
		Assertions.assertThat(appearances).isEqualTo(1);
		
	}
	
	/**
	 * number of times the number appeared on the last 10 draws
	 * @throws Exception
	 */
	@Test
	public void recentAppearancesFor2() throws Exception {
		// given
		int number = 2;
		int numberOfDraws = 5;
		
		// when
		int appearances = services.numberOfTimesWinningOnNDraws(beans20201009_small, number, numberOfDraws, isNumberOut);

		// then
		Assertions.assertThat(appearances).isEqualTo(2);
		
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void generalAppearances() throws Exception {
		// given
		int number = 22;
		int numberOfDraws = 30;
		
		// when
		int appearances = services.numberOfTimesWinningOnNDraws(beans20200922, number, numberOfDraws, isNumberOut);

		// then
		Assertions.assertThat(appearances).isEqualTo(2);
		
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void generalAppearancesForStar2() throws Exception {
		// given
		int number = 2;
		int numberOfDraws = 30;
		
		// when
		int appearances = services.numberOfTimesWinningOnNDraws(beans20200922, number, numberOfDraws, isStarOut);

		// then
		Assertions.assertThat(appearances).isEqualTo(6);
		
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void getstatsForNumber22() throws Exception {
		// given
		int number = 22;
		
		// when
		EuroMillionsStatsBean stats = services.statistics(beans20200922, number, isNumberOut, false);

		// then
		Assertions.assertThat(stats.getNumber()).isEqualTo(22);
		Assertions.assertThat(stats.getLastWinningDraw()).isEqualTo(5);
		Assertions.assertThat(stats.getWinningDrawAverage()).isEqualTo(13.30f);
		Assertions.assertThat(stats.getNumberOfTimesWinning()).isEqualTo(116);	
		Assertions.assertThat(String.format("%.2f", stats.getOutputFrequency())).isEqualTo("0,09");
		Assertions.assertThat(stats.getMaxBeforeWinningDraw()).isEqualTo(57);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn10Draws()).isEqualTo(1);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn30Draws()).isEqualTo(2);
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void getstatsForNumber10() throws Exception {
		// given
		int number = 10;
		
		// when
		EuroMillionsStatsBean stats = services.statistics(beans20200922, number, isNumberOut, false);

		// then
		Assertions.assertThat(stats.getNumber()).isEqualTo(10);
		Assertions.assertThat(stats.getLastWinningDraw()).isEqualTo(1);
		Assertions.assertThat(stats.getWinningDrawAverage()).isEqualTo(8.70f);
		Assertions.assertThat(stats.getNumberOfTimesWinning()).isEqualTo(146);	
		Assertions.assertThat(String.format("%.2f", stats.getOutputFrequency())).isEqualTo("0,11");
		Assertions.assertThat(stats.getMaxBeforeWinningDraw()).isEqualTo(38);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn10Draws()).isEqualTo(3);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn30Draws()).isEqualTo(5);
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void getstatsForStar2() throws Exception {
		// given
		int star = 2;
		
		// when
		EuroMillionsStatsBean stats = services.statistics(beans20200922, star, isStarOut, true);

		// then
		Assertions.assertThat(stats.getNumber()).isEqualTo(2);
		Assertions.assertThat(stats.getLastWinningDraw()).isEqualTo(15);
		Assertions.assertThat(stats.getWinningDrawAverage()).isEqualTo(3.55f);
		Assertions.assertThat(stats.getNumberOfTimesWinning()).isEqualTo(280);	
		Assertions.assertThat(String.format("%.2f", stats.getOutputFrequency())).isEqualTo("0,21");
		Assertions.assertThat(stats.getMaxBeforeWinningDraw()).isEqualTo(28);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn10Draws()).isEqualTo(0);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn30Draws()).isEqualTo(6);
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void getstatsForStar10() throws Exception {
		// given
		int star = 10;
		
		// when
		EuroMillionsStatsBean stats = services.statistics(beans20200922, star, isStarOut, true);

		// then
		Assertions.assertThat(stats.getNumber()).isEqualTo(10);
		Assertions.assertThat(stats.getLastWinningDraw()).isEqualTo(8);
		Assertions.assertThat(stats.getWinningDrawAverage()).isEqualTo(6.15f);
		Assertions.assertThat(stats.getNumberOfTimesWinning()).isEqualTo(179);	
		Assertions.assertThat(String.format("%.2f", stats.getOutputFrequency())).isEqualTo("0,13");
		Assertions.assertThat(stats.getMaxBeforeWinningDraw()).isEqualTo(28);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn10Draws()).isEqualTo(1);
		Assertions.assertThat(stats.getNumberOfTimesWinningOn30Draws()).isEqualTo(5);
	}
	
	@Test
	public void allStats() {
		List<EuroMillionsStatsBean> stats = RandomStatsUtils.allNumberStats(beans20200922);
		
		System.out.println(stats);
		List<EuroMillionsStatsBean> collect = stats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getWinningDrawAverage)).collect(Collectors.toList());
		collect.forEach(entry ->
		System.out.println(String.format("Number : %s | Max Winning Draw Average : %s", entry.getNumber(),
		entry.getWinningDrawAverage())));
	}
	
//	@Test
//	public void getLast10Draws() throws ParseException {
//		// given last draw date
//		int numberOfWeeks = 3;
//		
//		//when
//		List<EuroMillionsBean> listOfDrawsDuringTheLastXWeeks = services.getLastNDraws(beans20200922, numberOfWeeks);
//		
//		// then
//		assertThat(listOfDrawsDuringTheLastXWeeks.size()).isEqualTo(7);
//	}
	
	@Test
	public void getLast10ColdNumbers() {
		// given
		int nbWeeks = 4;
		List<Integer> expected = IntStream.of(1,2,3,4,5)
	            .boxed()
	            .collect(Collectors.toList());
		// when
		List<Integer> last10ColdNumbers = services.getLast10ColdNumbers(beans20201009, nbWeeks);
		
		// then
		assertThat(last10ColdNumbers).isEqualTo(expected);
	}

	@Test
	public void pronostics() throws Exception {
		//given
		long range = 20L;
		
		//when
		EuroMillionsPronosticsBean pronostics = services.pronostics(beans20200922, range);
		
		//then
		assertThat(pronostics.getAllStats()).isNotNull();
		assertThat(pronostics.getAllStats().size()).isEqualTo(62);
		System.out.println(pronostics);
		
//		LeastFrequentNumbers leastFrequentNumbers = euroMillionsPronosticsBean.getLeastFrequentNumbers();
//		LessAnticipatedNumbers lessAnticipatedNumbers = euroMillionsPronosticsBean.getLessAnticipatedNumbers();
//		MostAnticipatedNumbers mostAnticipatedNumbers = euroMillionsPronosticsBean.getMostAnticipatedNumbers();
//		
//		List<Integer>numbers =  leastFrequentNumbers.getNumbers();
//		List<Integer>stars =  leastFrequentNumbers.getStars();
//		
//		numbers =  lessAnticipatedNumbers.getNumbers();
//		stars =  lessAnticipatedNumbers.getStars();
//		
//		numbers =  mostAnticipatedNumbers.getNumbers();
//		stars =  mostAnticipatedNumbers.getStars();
		
	}
}
