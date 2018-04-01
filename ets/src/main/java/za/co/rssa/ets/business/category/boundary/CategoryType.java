package za.co.rssa.ets.business.category.boundary;

/**
 *
 * @author rida
 */
public enum CategoryType {
    PRODUCT("Product"), SUPPLIER("Supplier");
    
    private final String name;
    
    private CategoryType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
