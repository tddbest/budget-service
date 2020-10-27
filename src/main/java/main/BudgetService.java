package main;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BudgetService {
    private final IBudgetRepo repo;
    private Map<String, Budget> budgetMap;

    public double query(LocalDate start, LocalDate end) {
        List<Budget> allBudgets = repo.getAll();
        return allBudgets.stream()
                         .filter(budget -> budget.getYearMonth()
                                                 .equals(getYearMonthOfDate(start)))
                         .findFirst().get().getAmount();
        //LocalDate current = ;
        //return 0;
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
