import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FinanceHelperApp
{
    protected HashMap<String, Savings> savingsCollection;
    protected HashMap<String, Budget> budgetsCollection;

    public FinanceHelperApp()
    {
        savingsCollection = new HashMap<String, Savings>();
        budgetsCollection = new HashMap<String, Budget>();
    }

    public void populateTestData()
    {
        Savings eduSavings = new Savings("Education Fund", LocalDate.of(2024, 1, 1), 0.15, 10000);
        Savings tripSavings = new Savings("Trip Savings", LocalDate.of(2023, 6, 24), 0.10, 5000);
        Savings emergencySavings = new Savings("Emergency", LocalDate.of(2022, 1, 25), 0.12, 8000);

        savingsCollection.put(eduSavings.name, eduSavings);
        savingsCollection.put(tripSavings.name, tripSavings);
        savingsCollection.put(emergencySavings.name, emergencySavings);

        Budget petrolBudget = new Budget("Transport", Budget.category.transportation, LocalDate.of(2024, 11, 1), 1800);
        Budget groceries = new Budget("Groceries", Budget.category.food, LocalDate.of(2024, 11, 1), 2500);
        Budget outings = new Budget("Outings", Budget.category.entertainment, LocalDate.of(2024, 11, 1), 2000);

        budgetsCollection.put(petrolBudget.name, petrolBudget);
        budgetsCollection.put(groceries.name, groceries);
        budgetsCollection.put(outings.name, outings);
    }

    public void convertCurrencies(double origAmount, String convCurrencyCode)
    {
        // ISSUE
        // USD,  AUD, EUR, GBP
    }

    public double calcLoanRepay(double loanAmount, double interestRate, int numberOfPeriods)
    {
        double numerator = Math.pow((1+interestRate), numberOfPeriods) * interestRate; // r*(1+r)^n
        double denominator = Math.pow((1+interestRate), numberOfPeriods) -1; // (1+r)^n -1
        double monthlyRepayment = loanAmount * (numerator/denominator); // P * fraction

        return monthlyRepayment;
    }
    public static void main(String[] args) 
    {
        FinanceHelperApp application = new FinanceHelperApp();

        // TEST DATA
        application.populateTestData();

        System.out.println("Welcome to the Finance Helper Application!");
        System.out.println("What would you like to do?");
        System.out.println("1. Convert Currencies\n2. Calculate Loan Repayments\n3. View Savings Summary\n4. Create a New Saving Plan\n5. Edit Savings Value\n 6. View Budget Summary\n7. Create a New Budget Plan\n8. Edit Budget Value\n9. Exit");
        Scanner keyboard = new Scanner(System.in);
        String answer = keyboard.nextLine();

        while (!(answer.equals("9")))
        {
            switch (answer)
            {
                case "1":
                // USE API
                break;

                case "2":
                    System.out.println("Enter loan amount:");
                    double loan = Double.parseDouble(keyboard.nextLine());

                    System.out.println("Enter number of periods:");
                    int periods = Integer.parseInt(keyboard.nextLine());

                    System.out.println("Enter interest rate:");
                    double intRate = Double.parseDouble(keyboard.nextLine());

                    double loanRepayment = application.calcLoanRepay(loan, intRate, periods);

                    System.out.println("Loan Repayment Amount per Period: " + loanRepayment);
                break;

                case "3":
                    for (int i = 0; i < application.savingsCollection.size(); i++)
                    {
                        System.out.println(application.savingsCollection.get(i).name);
                        System.out.println("Current Balance: " + application.savingsCollection.get(i).runningValue);
                        System.out.println("-------------------------------");
                    }
                break;

                case "4":
                    System.out.println("Enter a Name for New Savings Plan:");
                    String name = keyboard.nextLine();

                    System.out.println("Enter interest rate:");
                    double newSaveRate = Double.parseDouble(keyboard.nextLine());

                    System.out.println("Enter Starting Date:");
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate startingDate = LocalDate.parse(keyboard.nextLine(), dateFormat);

                    System.out.println("Enter initial amount:");
                    double startAmount = Double.parseDouble(keyboard.nextLine());

                    Savings newSavingPlan = new Savings(name, startingDate, newSaveRate, startAmount);
                    application.savingsCollection.put(name, newSavingPlan);

                    System.out.println("Savings Plan " + name + " Successfully Recorded.");

                break;

                case "5":

                    System.out.println("Enter name of savings:");
                    Savings toEdit = application.savingsCollection.get(keyboard.nextLine());
                    
                    System.out.println("Enter amount to add to savings:");
                    double amountToAdd = keyboard.nextDouble();

                    toEdit.addToSavings(amountToAdd);
                    application.savingsCollection.put(toEdit.name, toEdit);
                break;

                case "6":
                for (int i = 0; i < application.budgetsCollection.size(); i++)
                {
                    System.out.println(application.budgetsCollection.get(i).name);
                    System.out.println("Current Balance: " + application.budgetsCollection.get(i).amountSoFar);
                    System.out.println("-------------------------------");
                }
                break;

                case "7":
                    System.out.println("Enter a Name for New Savings Plan:");
                    String budgetName = keyboard.nextLine();

                    System.out.println("Please select a category:\nBills\nTransportation\nFood\nHealthcare\nEntertainment\nMiscellaneous");
                    String category = keyboard.nextLine();
                    Budget.category categoryType;

                    switch (category.toLowerCase())
                    {
                        case "bills":
                            categoryType = Budget.category.bills;
                        break;

                        case "transportation":
                            categoryType = Budget.category.transportation;
                        break;

                        case "food":
                            categoryType = Budget.category.food;
                        break;

                        case "entertainment":
                            categoryType = Budget.category.entertainment;
                        break;

                        case "miscellaneous":
                            categoryType = Budget.category.miscellaneous;
                        break;

                        case "healthcare":
                            categoryType = Budget.category.healthcare;
                        break;

                        default:
                            categoryType = Budget.category.miscellaneous;
                        break;
                    }

                    System.out.println("Enter Starting Date:");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate start = LocalDate.parse(keyboard.nextLine(), format);

                    System.out.println("Enter budget amount:");
                    double maxAmount = Double.parseDouble(keyboard.nextLine());

                    Budget newBudgetPlan = new Budget(budgetName, categoryType, start, maxAmount);
                    application.budgetsCollection.put(budgetName, newBudgetPlan);

                    System.out.println("Budget Plan " + budgetName + " Successfully Recorded.");
                break;

                case "8":
                    System.out.println("Enter Budget Name:");
                    Budget budgetToEdit = application.budgetsCollection.get(keyboard.nextLine());

                    System.out.println("Enter Amount Spent from Budget:");
                    double amountToRemove = keyboard.nextDouble();

                    Boolean alert = budgetToEdit.updateAndAlert(amountToRemove);
                    if (alert)
                    {
                        System.out.println("ALERT: BUDGET EXCEEDED");
                    } else {
                        System.out.println("Remaining Value of Budget: " + budgetToEdit.calculateDifference());
                    }
                break;
            }

            System.out.println("What would you like to do?");
            System.out.println("1. Convert Currencies\n2. Calculate Loan Repayments\n3. View Savings Summary\n4. Create a New Saving Plan\n5. View Budget Summary\n6. Create a New Budget Plan\n7. Exit");
            answer = keyboard.nextLine();
        }
    }
}