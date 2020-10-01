package my.toolkit.randomstats.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.domain.EuroMillionsPronosticsBean;
import my.toolkit.randomstats.domain.EuroMillionsStatsBean;

@UtilityClass
@Slf4j
public class RandomStatsUtils {
	private static final int NB_NUMBERS = 50;
	private static final int NB_STARS = 12;
	private static final int GENERAL_FORM = 30;
	private static final int RECENTE_FORM = 10;
	private static final int PONCTUAL_DEVIATION = 20;

	private BiPredicate<Integer, EuroMillionsBean> isNumberOut = (number, bean) -> bean.getN1().compareTo(number)==0 || bean.getN2().compareTo(number)==0 || bean.getN3().compareTo(number)==0 || bean.getN4().compareTo(number)==0 || bean.getN5().compareTo(number)==0;
	
	private BiPredicate<Integer, EuroMillionsBean> isStarOut = (number, bean) -> bean.getE1().compareTo(number)==0 || bean.getE2().compareTo(number)==0;

	public static BiPredicate<Integer, EuroMillionsBean> getIsNumberOut() {
		return isNumberOut;
	}
	
	public static BiPredicate<Integer, EuroMillionsBean> getIsStarOut() {
		return isStarOut;
	}

	public static long numberOfTimesWinning(final List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return euroMillionsBeans.stream().
			filter(bean -> isOut.test(number, bean))
			.count();
			
	}
	
	public static long lastWinningDraw(List<EuroMillionsBean> euroMillionsBeans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return euroMillionsBeans.size() - euroMillionsBeans.stream()
				.filter(bean -> isOut.test(number, bean))
				.findFirst()
				.get().getNum()
				;
	}


	public static float winningDrawAverage(List<EuroMillionsBean> euroMillionsBeans, int number, int nbDraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		final int total = euroMillionsBeans.size();
		List<Integer> ponctualDeviations = new ArrayList<>();
		int current = -1;
		EuroMillionsBean last = null;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			if (ponctualDeviations.size() > nbDraws - 1) {
				break;
			}
			
			if (isOut.test(number, bean)) {
				if (current == -1) {
					current= total - bean.getNum();
				} else {
					current=  last.getNum() - bean.getNum();
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

	public static float outputFrequency(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		float filterDrawsWithNumber = numberOfTimesWinning(beans, number, isOut);
		float size = beans.size();
		return filterDrawsWithNumber / size;
	}
	
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
					current =  last.getNum() - bean.getNum();
				}
				last = bean;
				if (maxDeviation<current) {
					maxDeviation = current;
					max = bean;
				}
			}
		}		
		log.info("The bean for max Before Winning Draw : "+max);
		return maxDeviation;
	}
	
	public static int numberOfTimesWinningOnNDraws(List<EuroMillionsBean> euroMillionsBeans, int number, int nbDraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		int count=0;
		int index=-1;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			index++;
			if (index > nbDraws) {
				break;
			}
			
			if (isOut.test(number, bean)) {
				count++;
			}
		}		
		return count;
	}
	
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
		IntStream.rangeClosed(1, NB_STARS).forEach(number -> currentStats.add((statistics(euroMillionsBeans, number, isStarOut, true))));
		return currentStats;
	}
	
	public static List<EuroMillionsStatsBean> allNumberStats(List<EuroMillionsBean> euroMillionsBeans) {
		List<EuroMillionsStatsBean>currentStats = new ArrayList<>();
		IntStream.rangeClosed(1, NB_NUMBERS).forEach(number -> currentStats.add((statistics(euroMillionsBeans, number, isNumberOut, false))));
		return currentStats;
	}

	public static EuroMillionsPronosticsBean pronostics(List<EuroMillionsBean> euroMillionsBeans) {
		List<EuroMillionsStatsBean> allStats = allStats(euroMillionsBeans);
		
		// All Statistics for all numbers and stars
		EuroMillionsPronosticsBean pronostics = new EuroMillionsPronosticsBean(euroMillionsBeans);
		pronostics.setAllStats(allStats);
		
		List<EuroMillionsStatsBean> orderByWinningDrawAverage = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getWinningDrawAverage)).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByLastWinningDraw = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getLastWinningDraw)).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByOutputFrequency = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getOutputFrequency)).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByMaxBeforeWinningDraw = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getMaxBeforeWinningDraw)).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByNumberOfTimesWinningOn10Draws = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getNumberOfTimesWinningOn10Draws)).collect(Collectors.toList());
		List<EuroMillionsStatsBean> orderByNumberOfTimesWinningOn30Draws = allNumberStats(euroMillionsBeans).stream().sorted(Comparator.comparing(EuroMillionsStatsBean::getNumberOfTimesWinningOn30Draws)).collect(Collectors.toList());
		
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
}

