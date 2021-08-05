import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalAgreementTest {


    LocalDate independenceDayOnFriday = LocalDate.of(2014, 7, 4);
    LocalDate independenceDayOnSaturday = LocalDate.of(2015, 7, 4);
    LocalDate independenceDayOnSunday = LocalDate.of(2021, 7, 4);
    LocalDate independenceDayOnMonday = LocalDate.of(2016, 7, 4);

    LocalDate fridayBeforeIndependenceDay = LocalDate.of(2015, 7, 3);
    LocalDate mondayAfterIndependenceDay = LocalDate.of(2021, 7, 5);

    LocalDate laborDay = LocalDate.of(2021, 9, 6);


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekDayNotCharged() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", independenceDayOnMonday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekDayCharged() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("CHNS", independenceDayOnMonday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(1, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekendNotCharged() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKD", independenceDayOnSaturday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekendNotCharged2() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("CHNS", independenceDayOnSaturday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekendNotCharged3() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKD", independenceDayOnSaturday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekendChargedSaturday() throws Exception {
        // charged because independence day is observed on Friday instead of Saturday
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", independenceDayOnSaturday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(1, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDayWeekendChargedSunday() throws Exception {
        // charged because independence day is observed on Monday instead of Sunday
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", independenceDayOnSunday, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(1, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDaySaturdayNotChargedOnFriday() throws Exception {
        // not charged because independence day is observed on Friday instead of Saturday
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", fridayBeforeIndependenceDay, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysIndependenceDaySaturdayNotChargedOnMonday() throws Exception {
        // not charged because independence day is observed on Monday instead of Sunday
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", mondayAfterIndependenceDay, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysLaborDayCharged() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("CHNS", laborDay, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(1, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysLaborDayNotCharged() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", laborDay, 1, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(0, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysWholeWeek() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", independenceDayOnFriday.minusDays(4), 7, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(6, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysWholeWeek2() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("CHNS", independenceDayOnFriday.minusDays(4), 7, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(5, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateChargeDaysWholeWeek3() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKR", independenceDayOnFriday.minusDays(4), 7, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(4, rentalAgreement.getChargeDays());
    }

    @Test
    public void testCalculateDiscount() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", independenceDayOnFriday.minusDays(4), 2, 25, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        assertEquals(rentalAgreement.getDiscountAmount(), rentalAgreement.getPrediscountCharge() * .25);
        assertEquals(rentalAgreement.getFinalCharge(), rentalAgreement.getPrediscountCharge() * .75);
    }

    @Test
    public void test1() {
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                List<Tool> toolList = Main.initializeTools();
                CheckOutData checkOutData = new CheckOutData("JAKR", LocalDate.parse("09/03/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 5, 101, toolList);
                RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
                rentalAgreement.print();
            }
        });
    }

    @Test
    public void test2() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("LADW", LocalDate.parse("07/02/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 3, 10, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }

    @Test
    public void test3() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("CHNS", LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 5, 25, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }

    @Test
    public void test4() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKD", LocalDate.parse("09/03/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 6, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }

    @Test
    public void test5() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKR", LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 9, 0, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }

    @Test
    public void test6() throws Exception {
        List<Tool> toolList = Main.initializeTools();
        CheckOutData checkOutData = new CheckOutData("JAKR", LocalDate.parse("07/02/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")), 4, 50, toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }
}