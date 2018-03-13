package za.co.rssa.ets.business.product.presentation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class ExpenseListView implements Serializable {
    
    public List<ExpenseListViewTO> getExpenses() {
        List<ExpenseListViewTO> result = new ArrayList<>();
        result = getSampleData();
        return result;
    }
    
    private List<ExpenseListViewTO> getSampleData() {
        ExpenseListViewTO to1 = new ExpenseListViewTO();
        to1.setMonthOfPurchase("Mar");
        to1.setTransactionDate(new Date());
        to1.setProductCategory("Fruit");
        to1.setProductDescription("Apples");
        to1.setProductSize("1.5kg");
        to1.setUnit(1);
        to1.setPurchaseAmount(new BigDecimal("24.99"));
        to1.setComments("");
        
        ExpenseListViewTO to2 = new ExpenseListViewTO();
        to2.setMonthOfPurchase("Mar");
        to2.setTransactionDate(new Date());
        to2.setProductCategory("Fruit");
        to2.setProductDescription("Bananas");
        to2.setProductSize("2.5kg");
        to2.setUnit(2);
        to2.setPurchaseAmount(new BigDecimal("34.99"));
        to2.setComments("");
        
        ExpenseListViewTO to3 = new ExpenseListViewTO();
        to3.setMonthOfPurchase("Mar");
        to3.setTransactionDate(new Date());
        to3.setProductCategory("Vegetables");
        to3.setProductDescription("Pototoes");
        to3.setProductSize("3kg");
        to3.setUnit(1);
        to3.setPurchaseAmount(new BigDecimal("32.99"));
        to3.setComments("");
        
        ExpenseListViewTO to4 = new ExpenseListViewTO();
        to4.setMonthOfPurchase("Mar");
        to4.setTransactionDate(new Date());
        to4.setProductCategory("Canned food");
        to4.setProductDescription("Butter beans");
        to4.setProductSize("400g");
        to4.setUnit(1);
        to4.setPurchaseAmount(new BigDecimal("14.50"));
        to4.setComments("Stick to the Koo brand");
        
        List<ExpenseListViewTO> result = new ArrayList<>();
        result.add(to1);
        result.add(to2);
        result.add(to3);
        result.add(to4);
        return result;
    }
}
