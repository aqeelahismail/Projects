import java.time.*;
import java.time.temporal.ChronoUnit;

public class Savings {
    protected String name;
    protected LocalDate startDate;
    protected double interestRate;
    protected double startValue;
    protected double runningValue;
    
    public Savings(String name, LocalDate startDate, double interestRate, double startValue)
    {
        this.name = name;
        this.startDate = startDate;
        this.interestRate = interestRate;
        this.startValue = startValue;
        this.runningValue = startValue;
    }

    public double calcFutureValue(LocalDate endDate, double monthlyDeposits)
    {
        long numberOfPeriods = ChronoUnit.MONTHS.between(startDate, endDate); // n

        double numerator = Math.pow((1+this.interestRate), numberOfPeriods) - 1; // (1+i)^n -1
        double futureValue = (numerator/this.interestRate)*monthlyDeposits; // ((1+i)^n -1)/i * C
        return futureValue;
    }
    
    // WRONG FORMULA - FUTURE VALUE FORMULA
    public double calcMonthlyDeposits(LocalDate endDate, double targetValue)
    {
        long numberOfPeriods = ChronoUnit.MONTHS.between(startDate, endDate); // n

        double numerator = Math.pow((1+this.interestRate), numberOfPeriods) -1; // (1+i)^n -1 
        double denominator = this.interestRate; // i
        double fraction = numerator/denominator;

        double monthlyPayment = targetValue/fraction; // FV / fraction
        return monthlyPayment;
    }

    public int calcDuration(double targetValue, double monthlyDeposits)
    {
        double answer = ((targetValue/this.startValue)*this.interestRate)+1;
        double logAnswer = Math.log(answer);

        double base = 1+this.interestRate;
        double logBase = Math.log(base);

        int duration = (int) Math.ceil(logAnswer/logBase);
        return duration;
    }

    public void addToSavings(double amount)
    {
        this.runningValue = runningValue + amount;
    }
}
