import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Tool> toolList = initializeTools();
        CheckOutData checkOutData = checkOut(toolList);
        RentalAgreement rentalAgreement = new RentalAgreement(checkOutData);
        rentalAgreement.print();
    }

    public static CheckOutData checkOut(List<Tool> toolList) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a tool to rent by typing the Tool Code.");
        System.out.println();
        System.out.printf("%-15s%-15s%-15s\n", "Tool Type", "Brand", "ToolCode");
        for (Tool tool : toolList) {
            System.out.printf("%-15s%-15s%-15s\n", tool.getToolType(), tool.getBrand(), tool.getToolCode());
        }
        String toolCode = scanner.next().toUpperCase();
        System.out.println("Enter the Rental Day Count.");
        int rentalDays = scanner.nextInt();
        System.out.println("Enter the Discount Percentage");
        int discountPercentage = scanner.nextInt();
        System.out.println("Enter the Check Out Date (mm/dd/yyyy)");
        LocalDate checkOutDate = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return new CheckOutData(toolCode, checkOutDate, rentalDays, discountPercentage, toolList);
    }


    public static List<Tool> initializeTools() {
        List<Tool> toolList = new ArrayList<>();
        toolList.add(new Tool(ToolType.LADDER, "Werner", "LADW"));
        toolList.add(new Tool(ToolType.CHAINSAW, "Stihl", "CHNS"));
        toolList.add(new Tool(ToolType.JACKHAMMER, "Ridgid", "JAKR"));
        toolList.add(new Tool(ToolType.JACKHAMMER, "DeWalt", "JAKD"));
        return toolList;
    }
}
