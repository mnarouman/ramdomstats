package my.toolkit.randomstats.exception;

public class RandomStatsException extends Exception {

	public RandomStatsException() {
	}

	public RandomStatsException(String message) {
		super(message);
	}

	public RandomStatsException(Throwable cause) {
		super(cause);
	}

	public RandomStatsException(String message, Throwable cause) {
		super(message, cause);
	}

	public RandomStatsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
