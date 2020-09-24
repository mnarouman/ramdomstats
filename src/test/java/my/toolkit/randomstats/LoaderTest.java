package my.toolkit.randomstats;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import my.toolkit.randomstats.domain.EuroMillionsBean;
import my.toolkit.randomstats.services.RandomStatsServices;
import my.toolkit.randomstats.utils.RandomStatsUtils;
@Slf4j
@SpringBootTest
public class LoaderTest {
	private static final String CSV_EUROMILLIONS_2020_09_18 = "csv/euromillions-2020-09-18.csv";
	private static final String CSV_EUROMILLIONS_2020_09_22 = "csv/euromillions-2020-09-22.csv";
	private static final String CSV_EUROMILLIONS = "csv/euromillions.csv";
	private List<EuroMillionsBean> beans;
	private List<EuroMillionsBean> beans20200922;
	
	@Autowired
	private RandomStatsServices services;
	
	@BeforeEach
	public void init() {
		beans = services.loadBean(CSV_EUROMILLIONS);
		beans20200922 = services.loadBean(CSV_EUROMILLIONS_2020_09_22);
		log.debug("************ Number of draws :" + beans.size() );

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
	public void actualDeviation() throws Exception {
		// given
		int number = 22;
		
		// when
		long actualDeviation = services.actualDeviation(beans20200922, number);

		// then
		Assertions.assertThat(actualDeviation).isEqualTo(5);
		
	}
	
	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void ponctualDeviation22() throws Exception {
		// given
		int number = 22;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.ponctualDeviation(beans, number, nbDraws);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(13.30f);
		
	}
	
	/**
	 * Recent average deviation over its last 20 outings.
	 * @throws Exception
	 */
	@Test
	public void ponctualDeviation23() throws Exception {
		// given
		int number = 23;
		int nbDraws = 20;
		
		// when
		float ponctualDeviation = services.ponctualDeviation(beans, number, nbDraws);

		// then
		Assertions.assertThat(ponctualDeviation).isEqualTo(6.80f);
		
	}
	
	/**
	 * Output frequency
	 * @throws Exception
	 */
	@Test
	public void outputFrequency() throws Exception {
		// given
		String csvFileName = CSV_EUROMILLIONS_2020_09_22;
		int number = 22;
		
		// when
		float outputFrequency = services.outputFrequency(beans, number);

		// then
		Assertions.assertThat(outputFrequency).isEqualTo(0.09);
		
	}
	
	/**
	 * number of times the number is output
	 * @throws Exception
	 */
	@Test
	public void outputWinner() throws Exception {
		// given
		int number = 22;
		
		// when
		long outputWinner = services.outputWinner(beans, number);

		// then
		Assertions.assertThat(outputWinner).isEqualTo(116);
		
	}
	
	/**
	 * Maximum number of draws during which the number was delayed
	 * @throws Exception
	 */
	@Test
	public void maxDeviation() throws Exception {
		// given
		String csvFileName = CSV_EUROMILLIONS_2020_09_22;
		int number = 22;
		
		// when
		int maxDeviation = services.maxDeviation(beans, number);

		// then
		Assertions.assertThat(maxDeviation).isEqualTo(58);
		
	}
	
	/**
	 * number of times the number appeared on the last 10 draws
	 * @throws Exception
	 */
	@Test
	public void recentAppearances() throws Exception {
		// given
		String csvFileName = CSV_EUROMILLIONS_2020_09_22;
		int number = 22;
		int numberOfDraws = 10;
		
		// when
		int appearances = services.appearances(beans, number, numberOfDraws);

		// then
		Assertions.assertThat(appearances).isEqualTo(1);
		
	}
	
	/**
	 * number of times the number appeared on the last 30 draws
	 * @throws Exception
	 */
	@Test
	public void generalAppearances() throws Exception {
		// given
		String csvFileName = CSV_EUROMILLIONS_2020_09_22;
		int number = 22;
		int numberOfDraws = 30;
		
		// when
		int appearances = services.appearances(beans, number, numberOfDraws);

		// then
		Assertions.assertThat(appearances).isEqualTo(2);
		
	}
}
