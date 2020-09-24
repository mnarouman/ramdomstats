package my.toolkit.randomstats.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;
import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.exception.RandomStatsException;
import my.toolkit.randomstats.utils.RandomStatsUtils;

@Service
@Slf4j
public class RandomStatsServices {
	private static final String CSV_EUROMILLIONS = "csv/euromillions.csv";
	private final List<EuroMillionsBean> euroMillionsBeans = loadBean(CSV_EUROMILLIONS);

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
			log.error("Unable to parse or build the list of EuroMillionsBean", new RandomStatsException(e));
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
		} catch (IOException e) {
			log.error("Unable to load '" + csvFileName + "'",  new RandomStatsException(e));
		} catch (URISyntaxException e) {
			log.error("Unable to load '" + csvFileName + "'",  new RandomStatsException(e));
		}
		return reader;
	}

	public long actualDeviation(List<EuroMillionsBean> beans, int number) {
		return RandomStatsUtils.actualDeviation(beans, number);
	}

	public float ponctualDeviation(List<EuroMillionsBean> beans, int number, int nbdraws) {
		return RandomStatsUtils.ponctualDeviation(beans, number, nbdraws);
	}

	public float outputFrequency(List<EuroMillionsBean> beans, int number) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long outputWinner(List<EuroMillionsBean> beans, int number) {
		
		return RandomStatsUtils.filterDrawsWithNumber(beans, number);
	}

	public int maxDeviation(List<EuroMillionsBean> beans, int number) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int appearances(List<EuroMillionsBean> beans, int number, int numberOfDraws) {
		// TODO Auto-generated method stub
		return 0;
	}

}
