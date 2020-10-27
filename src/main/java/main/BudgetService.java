package main;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
public class BudgetService {
    private final IBudgetRepo repo;
    private Map<String, Budget> budgetMap;

    public BudgetService(IBudgetRepo repo) {
        this.repo = repo;
    }

    public double query(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            return 0;
        }
        List<Budget> allBudgets = repo.getAll();
        if (start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()) {
            return getEntireMonth(start, allBudgets);
        } else {
            int currentMonth = start.getMonthValue();
            double ans = 0;
            while (currentMonth < end.getMonthValue() + 1) {
                ans += getEntireMonth(LocalDate.of(start.getYear(), currentMonth, 1), allBudgets);
                currentMonth++;
            }
            return ans;
        }
        //LocalDate current = ;
        //return 0;
    }

    private int getEntireMonth(LocalDate start, List<Budget> allBudgets) {
        return allBudgets.stream()
                         .filter(budget -> budget.getYearMonth()
                                                 .equals(getYearMonthOfDate(start)))
                         .findFirst().get().getAmount();
    }

    private String getYearMonthOfDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyyMM"));
    }

    private double getFirstMonthBudget(LocalDate start) {
        int dayOfMonth = start.getDayOfMonth();
        return 0;
    }

    private double getLastMonthBudget(LocalDate end) {return 0;}

    private double getSingleDayBudget(LocalDate date) {
        int budgetOfMonth = budgetMap.get(getYearMonthOfDate(date)) == null ?
                            0 : budgetMap.get(getYearMonthOfDate(date)).getAmount();
        YearMonth yearMonthObj = YearMonth.of(date.getYear(), date.getMonthValue());
        int numberOfDay = yearMonthObj.lengthOfMonth();
        return budgetOfMonth/numberOfDay;
    }
}
