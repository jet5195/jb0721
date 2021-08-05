import java.time.LocalDate;
import java.util.List;

public class CheckOutData {
    private String toolCode;
    private int rentalDays;
    private LocalDate checkOutDate;
    private int discountPercentage;
    private Tool tool;

    public CheckOutData(String toolCode, LocalDate checkOutDate, int rentalDays, int discountPercentage, List<Tool> toolList) throws Exception {
        this.toolCode = toolCode;
        this.checkOutDate = checkOutDate;
        if (rentalDays < 1) {
            throw new Exception("Rental days must be 1 or greater");
        }
        this.rentalDays = rentalDays;
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new Exception("Discount percentage must be between 0 and 100.");
        }
        this.discountPercentage = discountPercentage;
        for (Tool tool : toolList) {
            if (tool.getToolCode().equals(this.toolCode)) {
                this.tool = tool;
            }
        }
        if (tool == null) {
            throw new Exception("Tool Code not found.");
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}
