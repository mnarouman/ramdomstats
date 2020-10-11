package my.toolkit.randomstats.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiPredicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.domain.EuroMillionsPronosticsBean;
import my.toolkit.randomstats.domain.EuroMillionsStatsBean;
import my.toolkit.randomstats.exception.RandomStatsException;
import my.toolkit.randomstats.utils.RandomStatsUtils;

@Service
public class RandomStatsServices {
	private static final String CSV_EUROMILLIONS = "csv/euromillions.csv";
	private final List<EuroMillionsBean> euroMillionsBeans = loadBean(CSV_EUROMILLIONS);
	private final static Logger LOG = LoggerFactory.getLogger(RandomStatsServices.class);
	public List<EuroMillionsBean> loadBean(final String csvFileName) {
		List<EuroMillionsBean> beans = null;
		try {
			beans = new CsvToBeanBuilder<EuroMillionsBean>(getReader(csvFileName))
						.withSeparator(';')
						.withType(EuroMillionsBean.class)
						.build()
						.parse();
			return beans;
		} catch (IllegalStateException e) {
			LOG.error("Unable to parse or build the list of EuroMillionsBean", new RandomStatsException(e));
		} 
		return beans;
	}

	public RandomStatsServices() throws  IOException{
		super();
	}

	private Reader getReader(final String csvFileName) {
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(
					Paths.get(
						ClassLoader.getSystemResource(csvFileName).toURI()
					)
			);
		} catch (IOException | URISyntaxException e) {
			LOG.error("Unable to load '" + csvFileName + "'",  new RandomStatsException(e));
		} 
		return reader;
	}

	public long lastWinningDraw(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return RandomStatsUtils.lastWinningDraw(beans == null ? euroMillionsBeans : beans, number, isOut);
	}

	public float winningDrawAverage(List<EuroMillionsBean> beans, int number, int nbdraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return RandomStatsUtils.winningDrawAverage(beans == null ? euroMillionsBeans : beans, number, nbdraws, isOut);
	}

	public float outputFrequency(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return RandomStatsUtils.outputFrequency(beans == null ? euroMillionsBeans : beans, number, isOut);
	}

	public long numberOfTimesWinning(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {

		return RandomStatsUtils.numberOfTimesWinning(beans == null ? euroMillionsBeans : beans, number, isOut);
	}

	public int maxBeforeWinningDraw(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return RandomStatsUtils.maxBeforeWinningDraw(beans == null ? euroMillionsBeans : beans, number, isOut);
	}

	public int numberOfTimesWinningOnNDraws(List<EuroMillionsBean> beans, int number, int numberOfDraws, BiPredicate<Integer, EuroMillionsBean> isOut) {
		return RandomStatsUtils.numberOfTimesWinningOnNDraws(beans == null ? euroMillionsBeans : beans, number, numberOfDraws, isOut);
	}

	public EuroMillionsStatsBean statistics(List<EuroMillionsBean> beans, int number, BiPredicate<Integer, EuroMillionsBean> isOut, boolean isStar) {
		return RandomStatsUtils.statistics(beans == null ? euroMillionsBeans : beans, number, isOut, isStar);
	}

	public EuroMillionsPronosticsBean pronostics(List<EuroMillionsBean> beans, long range) {
		return RandomStatsUtils.pronostics(beans == null ? euroMillionsBeans : beans, range);
	}

}
