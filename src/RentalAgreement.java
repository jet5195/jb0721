import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class RentalAgreement {
    private String toolCode;
    private ToolType toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double prediscountCharge;
    private int discountPercentage;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(CheckOutData checkOutData) {
        this.toolCode = checkOutData.getToolCode();
        this.rentalDays = checkOutData.getRentalDays();
        this.checkOutDate = checkOutData.getCheckOutDate();
        this.discountPercentage = checkOutData.getDiscountPercentage();
        this.toolCode = checkOutData.getToolCode();
        this.toolType = checkOutData.getTool().getToolType();
        this.toolBrand = checkOutData.getTool().getBrand();
        this.dailyRentalCharge = checkOutData.getTool().getToolType().dailyCharge;
        calculateFields();
    }

    private void calculateFields() {
        this.dueDate = checkOutDate.plusDays(rentalDays);
        this.chargeDays = this.calculateChargeDays();
        this.prediscountCharge = chargeDays * getDailyRentalCharge();
        this.discountAmount = getPrediscountCharge() * (getDiscountPercentage() / 100.00);
        this.finalCharge = getPrediscountCharge() - getDiscountAmount();
    }

    private int calculateChargeDays() {
        int weekendCount = 0;
        int holidayCount = 0;
        int weekendAndHolidayCount = 0;
        LocalDate iDay = getCheckOutDate();
        for (int i = 0; i < getRentalDays(); i++) {
            if (iDay.getDayOfWeek() == DayOfWeek.SATURDAY || iDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekendCount++;
                weekendAndHolidayCount++;
            }
            //Check for Labor Day
            if (iDay.getMonth() == Month.SEPTEMBER && iDay.getDayOfWeek() == DayOfWeek.MONDAY && iDay.getDayOfMonth() < 8) {
                holidayCount++;
                weekendAndHolidayCount++;
            }
            //Check for Independence Day
            else if (iDay.getMonth() == Month.JULY) {
                DayOfWeek dayOfWeek = iDay.getDayOfWeek();
                if ((iDay.getDayOfMonth() == 4 && iDay.getDayOfWeek() != DayOfWeek.SATURDAY && iDay.getDayOfWeek() != DayOfWeek.SUNDAY)
                        || (iDay.getDayOfMonth() == 3 && iDay.getDayOfWeek() == DayOfWeek.FRIDAY)
                        || (iDay.getDayOfMonth() == 5 && iDay.getDayOfWeek() == DayOfWeek.MONDAY)) {
                    holidayCount++;
                    weekendAndHolidayCount++;
                }
            }
            iDay = iDay.plusDays(1);
        }
        int chargeDays = getRentalDays();
        if (!toolType.weekendCharge && !toolType.holidayCharge) {
            chargeDays -= weekendAndHolidayCount;
        } else if (!toolType.weekendCharge) {
            chargeDays -= weekendCount;
        } else if (!toolType.holidayCharge) {
            chargeDays -= holidayCount;
        }
        return chargeDays;
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPrediscountCharge() {
        return prediscountCharge;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void print() {
        System.out.printf("%-25s%s\n", "Tool code:", getToolCode());
        System.out.printf("%-25s%s\n", "Tool type:", getToolType());
        System.out.printf("%-25s%s\n", "Tool brand:", getToolBrand());
        System.out.printf("%-25s%d\n", "Rental days:", getRentalDays());
        System.out.printf("%-25s%s\n", "CheckOut date:", getCheckOutDate().toString());
        System.out.printf("%-25s%s\n", "Due date:", getDueDate().toString());
        System.out.printf("%-25s$%.2f\n", "Daily rental charge:", getDailyRentalCharge());
        System.out.printf("%-25s%d\n", "Charge days:", getChargeDays());
        System.out.printf("%-25s$%.2f\n", "Pre-discount charge:", getPrediscountCharge());
        System.out.printf("%-25s%d%%\n", "Discount percent:", getDiscountPercentage());
        System.out.printf("%-25s$%.2f\n", "Discount amount:", getDiscountAmount());
        System.out.printf("%-25s$%.2f\n", "Final charge:", getFinalCharge());
    }
}
