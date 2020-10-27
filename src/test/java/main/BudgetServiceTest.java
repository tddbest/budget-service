package main;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BudgetServiceTest {

    IBudgetRepo repo = Mockito.mock(IBudgetRepo.class);

    @Test
    public void test_one_month() {
        when(repo.getAll()).thenReturn(Collections.singletonList(Budget.builder()
                                                                       .yearMonth("202009")
                                                                       .amount(300)
                                                                       .build()));
        BudgetService budgetService = new BudgetService(repo);
        Assert.assertEquals(300, budgetService.query(LocalDate.of(2020, 9, 1),
                                                     LocalDate.of(2020, 9, 30)));

    }
}
