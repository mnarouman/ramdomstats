package my.toolkit.randomstats.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.domain.EuroMillionsPronosticsBean;
import my.toolkit.randomstats.domain.EuroMillionsStatsBean;

public class RandomStatsUtils {
	private final static Logger LOG = LoggerFactory.getLogger(RandomStatsUtils.class);
	private static final int NB_NUMBERS = 50;
	private static final int NB_STARS = 12;
	private static final int GENERAL_FORM = 30;
	private static final int RECENTE_FORM = 10;
	private static final int PONCTUAL_DEVIATION = 20;

	private static final BiPredicate<Integer, EuroMillionsBean> isNumberOut = (number, bean) -> bean.getN1().compareTo(number)==0 || bean.getN2().compareTo(number)==0 || bean.getN3().compareTo(number)==0 || bean.getN4().compareTo(number)==0 || bean.getN5().compareTo(number)==0;
	
	private static final BiPredicate<Integer, EuroMillionsBean> isStarOut = (number, bean) -> bean.getE1().compareTo(number)==0 || bean.getE2().compareTo(number)==0;

	public static BiPredicate<Integer, EuroMillionsBean> getIsNumberOut() {
		return isNumberOut;
	}
	
	public static BiPredicate<Integer, EuroMillionsBean> getIsStarOut() {
		return isStarOut;
	}

	/**
	 * Nombre de fois gagnant
	 * @param euroMillionsBeans
	 * @param number
	 * @param isOut
	 * @return
	 */
	public static long numberOfTimesWinning(final List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return euroMillionsBeans.stream().
			filter(bean -> isOut.test(number, bean))
			.count();
			
	}
	
	/**
	 * Dernier tirage gagnant
	 * @param euroMillionsBeans
	 * @param number
	 * @param isOut
	 * @return
	 */
	public static long lastWinningDraw(List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return euroMillionsBeans.size() - euroMillionsBeans.stream()
				.filter(bean -> isOut.test(number, bean))
				.findFirst()
				.get().getNum();
	}

	/**
	 * Combien de fois en moyenne le numéro est-il gagnant ?
	 * @param euroMillionsBeans
	 * @param number
	 * @param nbDraws
	 * @param isOut
	 * @return
	 */
	public static float winningDrawAverage(List<EuroMillionsBean> euroMillionsBeans, int number, int nbDraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		final int total = euroMillionsBeans.stream().map(bean -> bean.getNum()).max(Comparator.naturalOrder()).get();
		List<Integer> ponctualDeviations = new ArrayList<>();
		int current = -1;
		EuroMillionsBean last = null;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			if (ponctualDeviations.size() > nbDraws - 1) {
				break;
			}
			
			if (isOut.test(number, bean)) {
				if (current == -1) {
					current = total - bean.getNum();
				} else {
					current = last.getNum() - bean.getNum() - 1;
				}
				last = bean;
				ponctualDeviations.add(current);
			}
		}		
		
		OptionalDouble average = ponctualDeviations.stream() //
			    .mapToInt(i -> i) //
			    .average() ;
				
		return average.isPresent() ? (float)average.getAsDouble() : 0;
				
	}

	/**
	 * frequence de sortie du numéro
	 * @param beans
	 * @param number
	 * @param isOut
	 * @return
	 */
	public static float outputFrequency(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		float filterDrawsWithNumber = numberOfTimesWinning(beans, number, isOut);
		float size = beans.size();
		return filterDrawsWithNumber / size;
	}
	
	/**
	 * 
	 * @param euroMillionsBeans
	 * @param number
	 * @param isOut
	 * @return
	 */
	public static int maxBeforeWinningDraw(List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		final int total = euroMillionsBeans.size();
		int current = -1;
		EuroMillionsBean last = null;
		EuroMillionsBean max = null;
		int maxDeviation = -1;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			
			if (isOut.test(number, bean)) {
				if (current == -1) {
					current = total - bean.getNum();
				} else {
					current =  last.getNum() - bean.getNum() - 1;
				}
				last = bean;
				if (maxDeviation < current) {
					maxDeviation = current;
					max = bean;
				}
			}
		}		
		LOG.info("The bean for max Before Winning Draw : " + max);
		return maxDeviation;
	}
	
	public static int numberOfTimesWinningOnNDraws(List<EuroMillionsBean> euroMillionsBeans, int number, int nbDraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		int count=0;
		int index=0;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			if (index >= nbDraws) {
				break;
			}
			
			if (isOut.test(number, bean)) {
				count++;
			}
			index++;
		}		
		return count;
	}
	
	/**
	 * 
	 * @param euroMillionsBeans
	 * @param number
	 * @param isOut
	 * @param isStar
	 * @return
	 */
	public static EuroMillionsStatsBean statistics(List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut, boolean isStar) {
		return new EuroMillionsStatsBean(
				number, 
				lastWinningDraw(euroMillionsBeans, number, isOut), 
				winningDrawAverage(euroMillionsBeans, number, PONCTUAL_DEVIATION, isOut), 
				outputFrequency(euroMillionsBeans, number, isOut), 
				numberOfTimesWinning(euroMillionsBeans, number, isOut), 
				maxBeforeWinningDraw(euroMillionsBeans, number, isOut), 
				numberOfTimesWinningOnNDraws(euroMillionsBeans, number,RECENTE_FORM, isOut), 
				numberOfTimesWinningOnNDraws(euroMillionsBeans, number,GENERAL_FORM, isOut),
				isStar);
	}
	
	public static List<EuroMillionsStatsBean> allStarsStats(List<EuroMillionsBean> euroMillionsBeans) {
		List<EuroMillionsStatsBean>currentStats = new ArrayList<>();
		IntStream.rangeClosed(1, NB_STARS)
			.forEach(
				number -> currentStats.add(
					statistics(euroMillionsBeans, number, isStarOut, true)
				)
			);
		return currentStats;
	}
	
	public static List<EuroMillionsStatsBean> allNumberStats(List<EuroMillionsBean> euroMillionsBeans) {
		List<EuroMillionsStatsBean>currentStats = new ArrayList<>();
		IntStream.rangeClosed(1, NB_NUMBERS)
			.forEach(
				number -> currentStats.add(
					statistics(euroMillionsBeans, number, isNumberOut, false)
				)
			);
		return currentStats;
	}

	public static EuroMillionsPronosticsBean pronostics(List<EuroMillionsBean> euroMillionsBeans, long range) {
		List<EuroMillionsStatsBean> allStats = allStats(euroMillionsBeans);
		
		// All Statistics for all numbers and stars
		EuroMillionsPronosticsBean pronostics = new EuroMillionsPronosticsBean(euroMillionsBeans);
		pronostics.setAllStats(allStats);
		
		List<EuroMillionsStatsBean> allNumberStats = allNumberStats(euroMillionsBeans);
		List<EuroMillionsStatsBean> orderByWinningDrawAverage = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getWinningDrawAverage)).limit(range).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByLastWinningDraw = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getLastWinningDraw)).limit(range).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByOutputFrequency = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getOutputFrequency)).limit(range).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByMaxBeforeWinningDraw = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getMaxBeforeWinningDraw)).limit(range).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByNumberOfTimesWinningOn10Draws = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getNumberOfTimesWinningOn10Draws)).limit(range).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByNumberOfTimesWinningOn30Draws = allNumberStats.stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getNumberOfTimesWinningOn30Draws)).limit(range).collect(Collectors.toList());
		
		// LeastFrequentNumbers
//		LeastFrequentNumbers leastFrequentNumbers;
//		pronostics.setLeastFrequentNumbers(leastFrequentNumbers);
				
		
		return pronostics;
	}

	public static List<EuroMillionsStatsBean> allStats(List<EuroMillionsBean> euroMillionsBeans) {
		List<EuroMillionsStatsBean> allStats = allNumberStats(euroMillionsBeans);
		allStats.addAll(allStarsStats(euroMillionsBeans));
		return allStats;
	}
	
	private final static  BiFunction<Calendar, Calendar, Long> dayDiff = (Calendar c1, Calendar c2) -> {
		long date2 = c2.getTime().getTime();
		long date1 = c1.getTime().getTime();
		long dateDiff = Math.abs(date2 - date1);
		long convertInDay = TimeUnit.DAYS.convert(dateDiff, TimeUnit.MILLISECONDS);
		return convertInDay;
	};

	public static List<EuroMillionsBean> getLastNDraws(List<EuroMillionsBean> beans, int numberOfWeeks) {
		Calendar currentDate = Calendar.getInstance(TimeZone.getDefault());

		List<EuroMillionsBean> listOfDrawsDuringTheLastXWeeks = beans.stream()
												.filter(bean -> {
													return dayDiff.apply(currentDate, bean.getDate()) <= (numberOfWeeks * 7) ;
												} )
												.collect(Collectors.toList());
		return listOfDrawsDuringTheLastXWeeks;
	}

	public static List<Integer> getLast10ColdNumbers(List<EuroMillionsBean> beans, int numberOfWeeks) {
		return IntStream.of(1,2,3,4,5)
	            .boxed()
	            .collect(Collectors.toList());
	}

}

