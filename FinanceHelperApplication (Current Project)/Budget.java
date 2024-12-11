import java.time.*;

public class Budget {
    protected String name;
    protected LocalDate startDate;
    protected double maxAmount;
    protected category type;
    protected Boolean maxReached;
    protected double amountSoFar;

    protected enum category
    {
        bills,
        food,
        entertainment,
        healthcare,
        transportation,
        miscellaneous
    }

    public Budget(String name, category type, LocalDate startDate, double maxAmount)
    {
        this.name = name;
        this.startDate = startDate;
        this.maxAmount = maxAmount;
        this.type = type;
        this.amountSoFar = 0;
        this.maxReached = false;
    }

    public double calculateDifference()
    {
        return this.maxAmount - this.amountSoFar;
    }

    public Boolean updateAndAlert(double paidFromBudget)
    {
        this.amountSoFar = amountSoFar + paidFromBudget;
        if (this.amountSoFar>this.maxAmount)
        {
            this.maxReached = true;
        } else if (this.amountSoFar<this.maxAmount)
        {
            this.maxReached = false;
        }

        return this.maxReached;
    }

}
