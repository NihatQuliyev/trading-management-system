package express.az.tradingmanagementservice.model.constant;

public final class Constants {
    public static final String NAME_REGEX = "Name is required and should be between 3 and 50 characters";
    public static final String DESCRIPTION_REGEX = "Description should not exceed 255 characters";
    public static final String PRICE_REGEX = "Price should be a positive number";
    public static final String SAVE_SUCCESSFULLY = "Save successfully!";
    public static final String FIND_PRODUCT = "Product successful show found for id";
    public static final String DELETE_BY_ID_SUCCESSFULLY = "According to the id, the product was successfully deleted";
    public static final String UPDATE_SUCCESSFULLY = "Update successfully product";
    public static final String PRODUCT_SHOW_SUCCESS = "Product were successfully printed";
    public static final String CATEGORY_REGEX = "Category id must bigger than 0";
    public static final String CATEGORY_URGENT = "Category should be valid and existing entity";
    public static final String CATEGORIES_SHOW_SUCCESS = "Categories were successfully printed!";
    public static final String SUPPLIER_REGEX = "Supplier id must bigger than 0";
    public static final String SUPPLIER_URGENT = "Supplier should be valid and existing entity";
    public static final String SUPPLIER_SHOW_SUCCESS = "Supplier were successfully printed!";

    public static final String FIND_CATEGORY = "Category successful show found for id";
    public static final String FIND_SUPPLIER = "Supplier successful show found for id";
    public static final String USER_REGISTER_SUCCESSFULLY = "User register successfully";
    public static final String USER_LOGIN_SUCCESSFULLY = "User login successfully";
    public static final String USER_ACTIVATION_SUCCESSFULLY = "User activation successfully";
    public static final String USERNAME_NOT_FOUND = "Username not found";

    public static final String SUBJECT_IS_URGENT = "Subject is urgent!";
    public static final String BODY_IS_URGENT = "Body is urgent!";
    public static final String NAME_IS_URGENT = "Name is urgent!";
    public static final String EMAIL_IS_URGENT = "Email is urgent!";
    public static final String EMAIL_IS_NOT_VALID = "Email is invalid!";
    public static final String PASSWORD_IS_NOT_VALID = "Password is invalid!";
    public static final String PASSWORD_REGEX = "Password must be at least 6 characters long and contain at least one letter and one digit";
    public static final String SUBJECT_REGEX = "Subject field length cannot exceed 100 characters";
}