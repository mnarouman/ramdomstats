package my.toolkit.randomstats.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import lombok.experimental.UtilityClass;
import my.toolkit.randomstats.domain.EuroMillionsBean;

@UtilityClass


public class RandomStatsUtils {
	public static long filterDrawsWithNumber(final List<EuroMillionsBean> euroMillionsBeans, int number) {
		return euroMillionsBeans.stream().
			filter(bean -> bean.getN1() == number || bean.getN2() == number || bean.getN3() == number || bean.getN4() == number || bean.getN5() == number)
			.count();
			
	}
	
	public static long actualDeviation(List<EuroMillionsBean> euroMillionsBeans, int number) {
		return euroMillionsBeans.size() - euroMillionsBeans.stream()
				.filter(bean -> bean.getN1() == number || bean.getN2() == number || bean.getN3() == number || bean.getN4() == number || bean.getN5() == number)
				.findFirst()
				.get().getNum()
				;
	}


	public static float ponctualDeviation(List<EuroMillionsBean> euroMillionsBeans, int number, int nbDraws) {
		final int total = euroMillionsBeans.size();
		List<Integer> ponctualDeviations = new ArrayList<>();
		int current = -1;
		EuroMillionsBean last = null;
		for (EuroMillionsBean bean : euroMillionsBeans) {
			if (ponctualDeviations.size() > nbDraws - 1) {
				break;
			}
			
			boolean isOut = bean.getN1() == number || bean.getN2() == number || bean.getN3() == number || bean.getN4() == number || bean.getN5() == number;
			if (isOut) {
				if (current == -1) {
					current= total - bean.getNum();
				} else {
					current=  last.getNum() - bean.getNum() - 1;
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

}
