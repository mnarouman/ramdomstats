package my.toolkit.randomstats.domain;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

@Component
public class EuroMillionsBean {
	public EuroMillionsBean(Integer num, Calendar date, Integer jackpot, Integer n1, Integer n2, Integer n3, Integer n4,
			Integer n5, Integer e1, Integer e2, Long nbGames, long eu1, long eu2, long eu3, long eu4, long eu5,
			long eu6, long eu7, long eu8, long eu9, long eu10, long eu11, long eu12, float nb1, float nb2, float nb3,
			float nb4, float nb5, float nb6, float nb7, float nb8, float nb9, float nb10, float nb11, float nb12,
			float mt1, float mt2, float mt3, float mt4, float mt5, float mt6, float mt7, float mt8, float mt9,
			float mt10, float mt11, float mt12) {
		super();
		this.num = num;
		this.date = date;
		this.jackpot = jackpot;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		this.n4 = n4;
		this.n5 = n5;
		this.e1 = e1;
		this.e2 = e2;
		this.nbGames = nbGames;
		this.eu1 = eu1;
		this.eu2 = eu2;
		this.eu3 = eu3;
		this.eu4 = eu4;
		this.eu5 = eu5;
		this.eu6 = eu6;
		this.eu7 = eu7;
		this.eu8 = eu8;
		this.eu9 = eu9;
		this.eu10 = eu10;
		this.eu11 = eu11;
		this.eu12 = eu12;
		this.nb1 = nb1;
		this.nb2 = nb2;
		this.nb3 = nb3;
		this.nb4 = nb4;
		this.nb5 = nb5;
		this.nb6 = nb6;
		this.nb7 = nb7;
		this.nb8 = nb8;
		this.nb9 = nb9;
		this.nb10 = nb10;
		this.nb11 = nb11;
		this.nb12 = nb12;
		this.mt1 = mt1;
		this.mt2 = mt2;
		this.mt3 = mt3;
		this.mt4 = mt4;
		this.mt5 = mt5;
		this.mt6 = mt6;
		this.mt7 = mt7;
		this.mt8 = mt8;
		this.mt9 = mt9;
		this.mt10 = mt10;
		this.mt11 = mt11;
		this.mt12 = mt12;
	}
	public EuroMillionsBean() {
		super();
	}
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Integer getJackpot() {
		return jackpot;
	}
	public void setJackpot(Integer jackpot) {
		this.jackpot = jackpot;
	}
	public Integer getN1() {
		return n1;
	}
	public void setN1(Integer n1) {
		this.n1 = n1;
	}
	public Integer getN2() {
		return n2;
	}
	public void setN2(Integer n2) {
		this.n2 = n2;
	}
	public Integer getN3() {
		return n3;
	}
	public void setN3(Integer n3) {
		this.n3 = n3;
	}
	public Integer getN4() {
		return n4;
	}
	public void setN4(Integer n4) {
		this.n4 = n4;
	}
	public Integer getN5() {
		return n5;
	}
	public void setN5(Integer n5) {
		this.n5 = n5;
	}
	public Integer getE1() {
		return e1;
	}
	public void setE1(Integer e1) {
		this.e1 = e1;
	}
	public Integer getE2() {
		return e2;
	}
	public void setE2(Integer e2) {
		this.e2 = e2;
	}
	public Long getNbGames() {
		return nbGames;
	}
	public void setNbGames(Long nbGames) {
		this.nbGames = nbGames;
	}
	public long getEu1() {
		return eu1;
	}
	public void setEu1(long eu1) {
		this.eu1 = eu1;
	}
	public long getEu2() {
		return eu2;
	}
	public void setEu2(long eu2) {
		this.eu2 = eu2;
	}
	public long getEu3() {
		return eu3;
	}
	public void setEu3(long eu3) {
		this.eu3 = eu3;
	}
	public long getEu4() {
		return eu4;
	}
	public void setEu4(long eu4) {
		this.eu4 = eu4;
	}
	public long getEu5() {
		return eu5;
	}
	public void setEu5(long eu5) {
		this.eu5 = eu5;
	}
	public long getEu6() {
		return eu6;
	}
	public void setEu6(long eu6) {
		this.eu6 = eu6;
	}
	public long getEu7() {
		return eu7;
	}
	public void setEu7(long eu7) {
		this.eu7 = eu7;
	}
	public long getEu8() {
		return eu8;
	}
	public void setEu8(long eu8) {
		this.eu8 = eu8;
	}
	public long getEu9() {
		return eu9;
	}
	public void setEu9(long eu9) {
		this.eu9 = eu9;
	}
	public long getEu10() {
		return eu10;
	}
	public void setEu10(long eu10) {
		this.eu10 = eu10;
	}
	public long getEu11() {
		return eu11;
	}
	public void setEu11(long eu11) {
		this.eu11 = eu11;
	}
	public long getEu12() {
		return eu12;
	}
	public void setEu12(long eu12) {
		this.eu12 = eu12;
	}
	public float getNb1() {
		return nb1;
	}
	public void setNb1(float nb1) {
		this.nb1 = nb1;
	}
	public float getNb2() {
		return nb2;
	}
	public void setNb2(float nb2) {
		this.nb2 = nb2;
	}
	public float getNb3() {
		return nb3;
	}
	public void setNb3(float nb3) {
		this.nb3 = nb3;
	}
	public float getNb4() {
		return nb4;
	}
	public void setNb4(float nb4) {
		this.nb4 = nb4;
	}
	public float getNb5() {
		return nb5;
	}
	public void setNb5(float nb5) {
		this.nb5 = nb5;
	}
	public float getNb6() {
		return nb6;
	}
	public void setNb6(float nb6) {
		this.nb6 = nb6;
	}
	public float getNb7() {
		return nb7;
	}
	public void setNb7(float nb7) {
		this.nb7 = nb7;
	}
	public float getNb8() {
		return nb8;
	}
	public void setNb8(float nb8) {
		this.nb8 = nb8;
	}
	public float getNb9() {
		return nb9;
	}
	public void setNb9(float nb9) {
		this.nb9 = nb9;
	}
	public float getNb10() {
		return nb10;
	}
	public void setNb10(float nb10) {
		this.nb10 = nb10;
	}
	public float getNb11() {
		return nb11;
	}
	public void setNb11(float nb11) {
		this.nb11 = nb11;
	}
	public float getNb12() {
		return nb12;
	}
	public void setNb12(float nb12) {
		this.nb12 = nb12;
	}
	public float getMt1() {
		return mt1;
	}
	public void setMt1(float mt1) {
		this.mt1 = mt1;
	}
	public float getMt2() {
		return mt2;
	}
	public void setMt2(float mt2) {
		this.mt2 = mt2;
	}
	public float getMt3() {
		return mt3;
	}
	public void setMt3(float mt3) {
		this.mt3 = mt3;
	}
	public float getMt4() {
		return mt4;
	}
	public void setMt4(float mt4) {
		this.mt4 = mt4;
	}
	public float getMt5() {
		return mt5;
	}
	public void setMt5(float mt5) {
		this.mt5 = mt5;
	}
	public float getMt6() {
		return mt6;
	}
	public void setMt6(float mt6) {
		this.mt6 = mt6;
	}
	public float getMt7() {
		return mt7;
	}
	public void setMt7(float mt7) {
		this.mt7 = mt7;
	}
	public float getMt8() {
		return mt8;
	}
	public void setMt8(float mt8) {
		this.mt8 = mt8;
	}
	public float getMt9() {
		return mt9;
	}
	public void setMt9(float mt9) {
		this.mt9 = mt9;
	}
	public float getMt10() {
		return mt10;
	}
	public void setMt10(float mt10) {
		this.mt10 = mt10;
	}
	public float getMt11() {
		return mt11;
	}
	public void setMt11(float mt11) {
		this.mt11 = mt11;
	}
	public float getMt12() {
		return mt12;
	}
	public void setMt12(float mt12) {
		this.mt12 = mt12;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((e1 == null) ? 0 : e1.hashCode());
		result = prime * result + ((e2 == null) ? 0 : e2.hashCode());
		result = prime * result + (int) (eu1 ^ (eu1 >>> 32));
		result = prime * result + (int) (eu10 ^ (eu10 >>> 32));
		result = prime * result + (int) (eu11 ^ (eu11 >>> 32));
		result = prime * result + (int) (eu12 ^ (eu12 >>> 32));
		result = prime * result + (int) (eu2 ^ (eu2 >>> 32));
		result = prime * result + (int) (eu3 ^ (eu3 >>> 32));
		result = prime * result + (int) (eu4 ^ (eu4 >>> 32));
		result = prime * result + (int) (eu5 ^ (eu5 >>> 32));
		result = prime * result + (int) (eu6 ^ (eu6 >>> 32));
		result = prime * result + (int) (eu7 ^ (eu7 >>> 32));
		result = prime * result + (int) (eu8 ^ (eu8 >>> 32));
		result = prime * result + (int) (eu9 ^ (eu9 >>> 32));
		result = prime * result + ((jackpot == null) ? 0 : jackpot.hashCode());
		result = prime * result + Float.floatToIntBits(mt1);
		result = prime * result + Float.floatToIntBits(mt10);
		result = prime * result + Float.floatToIntBits(mt11);
		result = prime * result + Float.floatToIntBits(mt12);
		result = prime * result + Float.floatToIntBits(mt2);
		result = prime * result + Float.floatToIntBits(mt3);
		result = prime * result + Float.floatToIntBits(mt4);
		result = prime * result + Float.floatToIntBits(mt5);
		result = prime * result + Float.floatToIntBits(mt6);
		result = prime * result + Float.floatToIntBits(mt7);
		result = prime * result + Float.floatToIntBits(mt8);
		result = prime * result + Float.floatToIntBits(mt9);
		result = prime * result + ((n1 == null) ? 0 : n1.hashCode());
		result = prime * result + ((n2 == null) ? 0 : n2.hashCode());
		result = prime * result + ((n3 == null) ? 0 : n3.hashCode());
		result = prime * result + ((n4 == null) ? 0 : n4.hashCode());
		result = prime * result + ((n5 == null) ? 0 : n5.hashCode());
		result = prime * result + Float.floatToIntBits(nb1);
		result = prime * result + Float.floatToIntBits(nb10);
		result = prime * result + Float.floatToIntBits(nb11);
		result = prime * result + Float.floatToIntBits(nb12);
		result = prime * result + Float.floatToIntBits(nb2);
		result = prime * result + Float.floatToIntBits(nb3);
		result = prime * result + Float.floatToIntBits(nb4);
		result = prime * result + Float.floatToIntBits(nb5);
		result = prime * result + Float.floatToIntBits(nb6);
		result = prime * result + Float.floatToIntBits(nb7);
		result = prime * result + Float.floatToIntBits(nb8);
		result = prime * result + Float.floatToIntBits(nb9);
		result = prime * result + ((nbGames == null) ? 0 : nbGames.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EuroMillionsBean other = (EuroMillionsBean) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (e1 == null) {
			if (other.e1 != null)
				return false;
		} else if (!e1.equals(other.e1))
			return false;
		if (e2 == null) {
			if (other.e2 != null)
				return false;
		} else if (!e2.equals(other.e2))
			return false;
		if (eu1 != other.eu1)
			return false;
		if (eu10 != other.eu10)
			return false;
		if (eu11 != other.eu11)
			return false;
		if (eu12 != other.eu12)
			return false;
		if (eu2 != other.eu2)
			return false;
		if (eu3 != other.eu3)
			return false;
		if (eu4 != other.eu4)
			return false;
		if (eu5 != other.eu5)
			return false;
		if (eu6 != other.eu6)
			return false;
		if (eu7 != other.eu7)
			return false;
		if (eu8 != other.eu8)
			return false;
		if (eu9 != other.eu9)
			return false;
		if (jackpot == null) {
			if (other.jackpot != null)
				return false;
		} else if (!jackpot.equals(other.jackpot))
			return false;
		if (Float.floatToIntBits(mt1) != Float.floatToIntBits(other.mt1))
			return false;
		if (Float.floatToIntBits(mt10) != Float.floatToIntBits(other.mt10))
			return false;
		if (Float.floatToIntBits(mt11) != Float.floatToIntBits(other.mt11))
			return false;
		if (Float.floatToIntBits(mt12) != Float.floatToIntBits(other.mt12))
			return false;
		if (Float.floatToIntBits(mt2) != Float.floatToIntBits(other.mt2))
			return false;
		if (Float.floatToIntBits(mt3) != Float.floatToIntBits(other.mt3))
			return false;
		if (Float.floatToIntBits(mt4) != Float.floatToIntBits(other.mt4))
			return false;
		if (Float.floatToIntBits(mt5) != Float.floatToIntBits(other.mt5))
			return false;
		if (Float.floatToIntBits(mt6) != Float.floatToIntBits(other.mt6))
			return false;
		if (Float.floatToIntBits(mt7) != Float.floatToIntBits(other.mt7))
			return false;
		if (Float.floatToIntBits(mt8) != Float.floatToIntBits(other.mt8))
			return false;
		if (Float.floatToIntBits(mt9) != Float.floatToIntBits(other.mt9))
			return false;
		if (n1 == null) {
			if (other.n1 != null)
				return false;
		} else if (!n1.equals(other.n1))
			return false;
		if (n2 == null) {
			if (other.n2 != null)
				return false;
		} else if (!n2.equals(other.n2))
			return false;
		if (n3 == null) {
			if (other.n3 != null)
				return false;
		} else if (!n3.equals(other.n3))
			return false;
		if (n4 == null) {
			if (other.n4 != null)
				return false;
		} else if (!n4.equals(other.n4))
			return false;
		if (n5 == null) {
			if (other.n5 != null)
				return false;
		} else if (!n5.equals(other.n5))
			return false;
		if (Float.floatToIntBits(nb1) != Float.floatToIntBits(other.nb1))
			return false;
		if (Float.floatToIntBits(nb10) != Float.floatToIntBits(other.nb10))
			return false;
		if (Float.floatToIntBits(nb11) != Float.floatToIntBits(other.nb11))
			return false;
		if (Float.floatToIntBits(nb12) != Float.floatToIntBits(other.nb12))
			return false;
		if (Float.floatToIntBits(nb2) != Float.floatToIntBits(other.nb2))
			return false;
		if (Float.floatToIntBits(nb3) != Float.floatToIntBits(other.nb3))
			return false;
		if (Float.floatToIntBits(nb4) != Float.floatToIntBits(other.nb4))
			return false;
		if (Float.floatToIntBits(nb5) != Float.floatToIntBits(other.nb5))
			return false;
		if (Float.floatToIntBits(nb6) != Float.floatToIntBits(other.nb6))
			return false;
		if (Float.floatToIntBits(nb7) != Float.floatToIntBits(other.nb7))
			return false;
		if (Float.floatToIntBits(nb8) != Float.floatToIntBits(other.nb8))
			return false;
		if (Float.floatToIntBits(nb9) != Float.floatToIntBits(other.nb9))
			return false;
		if (nbGames == null) {
			if (other.nbGames != null)
				return false;
		} else if (!nbGames.equals(other.nbGames))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EuroMillionsBean [num=" + num  + ", n1=" + n1 + ", n2=" + n2
				+ ", n3=" + n3 + ", n4=" + n4 + ", n5=" + n5 + ", e1=" + e1 + ", e2=" + e2 + ", date=" + date + ", jackpot=" + jackpot + ", nbGames=" + nbGames
				+ ", eu1=" + eu1 + ", eu2=" + eu2 + ", eu3=" + eu3 + ", eu4=" + eu4 + ", eu5=" + eu5 + ", eu6=" + eu6
				+ ", eu7=" + eu7 + ", eu8=" + eu8 + ", eu9=" + eu9 + ", eu10=" + eu10 + ", eu11=" + eu11 + ", eu12="
				+ eu12 + ", nb1=" + nb1 + ", nb2=" + nb2 + ", nb3=" + nb3 + ", nb4=" + nb4 + ", nb5=" + nb5 + ", nb6="
				+ nb6 + ", nb7=" + nb7 + ", nb8=" + nb8 + ", nb9=" + nb9 + ", nb10=" + nb10 + ", nb11=" + nb11
				+ ", nb12=" + nb12 + ", mt1=" + mt1 + ", mt2=" + mt2 + ", mt3=" + mt3 + ", mt4=" + mt4 + ", mt5=" + mt5
				+ ", mt6=" + mt6 + ", mt7=" + mt7 + ", mt8=" + mt8 + ", mt9=" + mt9 + ", mt10=" + mt10 + ", mt11="
				+ mt11 + ", mt12=" + mt12 + "]";
	}
}
