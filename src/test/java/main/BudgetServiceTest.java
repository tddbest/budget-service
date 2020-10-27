package main;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BudgetServiceTest {

    IBudgetRepo repo;

    @Before
    public void setUp() throws Exception {
        repo = Mockito.mock(IBudgetRepo.class);
    }

    @Test
    public void test_one_month() {
        when(repo.getAll()).thenReturn(Collections.singletonList(new Budget("202009", 300)));
        BudgetService budgetService = new BudgetService(repo);
        assertThat(budgetService.query(LocalDate.of(2020, 9, 1),
                                                     LocalDate.of(2020, 9, 30))).isEqualTo(300.0);

    }

    @Test
    public void test_illegal_input() {
        when(repo.getAll()).thenReturn(Collections.emptyList());
        BudgetService budgetService = new BudgetService(repo);
        assertThat(budgetService.query(LocalDate.of(2020, 10, 1),
                                       LocalDate.of(2020, 9, 30))).isEqualTo(0.0);

    }
}
