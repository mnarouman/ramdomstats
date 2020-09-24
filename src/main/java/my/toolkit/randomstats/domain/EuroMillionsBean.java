package my.toolkit.randomstats.domain;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EuroMillionsBean {
	/**
	 * order number
	 */
	@CsvBindByName
	private Integer num;
	
	/**
	 * The draw date
	 */
	@CsvDate("yyyy-MM-dd")
	@CsvBindByName
	private Calendar date; 

	/**
	 * Jackpot amount announced
	 */
	@CsvBindByName
	private Integer jackpot;
	
	/**
	 * The 5 numbers of the draw (In ascending order or in output order)
	 */
	@CsvBindByName
	private Integer n1;
	@CsvBindByName
	private Integer n2;
	@CsvBindByName
	private Integer n3;
	@CsvBindByName
	private Integer n4;
	@CsvBindByName
	private Integer n5;
	
	/**
	 * The 2 stars of the draw (In ascending order or in output order)
	 */
	@CsvBindByName
	private Integer e1;
	@CsvBindByName
	private Integer e2;
	
	/**
	 * Number of combinations played in europe
	 */
	@CsvBindByName(column = "NBJEU")
	private Long nbGames;
	
	/**
	 * number of winners in europe on ranks 1 to 12
	 */
	@CsvBindByName(column = "EU1")
	private long eu1;
	@CsvBindByName(column = "EU2")
	private long eu2;
	@CsvBindByName(column = "EU3")
	private long eu3;
	@CsvBindByName(column = "EU4")
	private long eu4;
	@CsvBindByName(column = "EU5")
	private long eu5;
	@CsvBindByName(column = "EU6")
	private long eu6;
	@CsvBindByName(column = "EU7")
	private long eu7;
	@CsvBindByName(column = "EU8")
	private long eu8;
	@CsvBindByName(column = "EU9")
	private long eu9;
	@CsvBindByName(column = "EU10")
	private long eu10;
	@CsvBindByName(column = "EU11")
	private long eu11;
	@CsvBindByName(column = "EU12")
	private long eu12;
	
	/**
	 * number of winners from the requested country in rows 1 to 12
	 * (available only for France, Belgium, Irland, Portugal and Spain)
	 */
	@CsvBindByName(column = "NB1", locale="fr-FR")
	private float nb1;
	@CsvBindByName(column = "NB2", locale="fr-FR")
	private float nb2;
	@CsvBindByName(column = "NB3", locale="fr-FR")
	private float nb3;
	@CsvBindByName(column = "NB4", locale="fr-FR")
	private float nb4;
	@CsvBindByName(column = "NB5", locale="fr-FR")
	private float nb5;
	@CsvBindByName(column = "NB6", locale="fr-FR")
	private float nb6;
	@CsvBindByName(column = "NB7", locale="fr-FR")
	private float nb7;
	@CsvBindByName(column = "NB8", locale="fr-FR")
	private float nb8;
	@CsvBindByName(column = "NB9", locale="fr-FR")
	private float nb9;
	@CsvBindByName(column = "NB10", locale="fr-FR")
	private float nb10;
	@CsvBindByName(column = "NB11", locale="fr-FR")
	private float nb11;
	@CsvBindByName(column = "NB12", locale="fr-FR")
	private float nb12;
	
	/**
	 * Unit amount of earnings for the requested country (and in the country's currency)
	 */
	@CsvBindByName(column = "MT1", locale="fr-FR")
	private float mt1;
	@CsvBindByName(column = "MT2", locale="fr-FR")
	private float mt2;
	@CsvBindByName(column = "MT3", locale="fr-FR")
	private float mt3;
	@CsvBindByName(column = "MT4", locale="fr-FR")
	private float mt4;
	@CsvBindByName(column = "MT5", locale="fr-FR")
	private float mt5;
	@CsvBindByName(column = "MT6", locale="fr-FR")
	private float mt6;
	@CsvBindByName(column = "MT7", locale="fr-FR")
	private float mt7;
	@CsvBindByName(column = "MT8", locale="fr-FR")
	private float mt8;
	@CsvBindByName(column = "MT9", locale="fr-FR")
	private float mt9;
	@CsvBindByName(column = "MT10", locale="fr-FR")
	private float mt10;
	@CsvBindByName(column = "MT11", locale="fr-FR")
	private float mt11;
	@CsvBindByName(column = "MT12", locale="fr-FR")
	private float mt12;
}
