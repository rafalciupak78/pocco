package rekr.rest_work;

public interface IConstantLoan {

	public static Integer maxAmount = 1000000;
	public static Integer minAmount = 1000;
	public static Integer maxTerm = 64;
	public static Integer minTerm = 3;
	public static String OK = "Everything OK.";
	public static String NO1 = "It is not OK (amount, term). Sorry.";
	public static String NO2 = "It is not OK (time). Sorry.";
}
