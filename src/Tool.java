public class Tool {
    private ToolType toolType;
    private String brand;
    private String toolCode;

    public Tool(ToolType toolType, String brand, String toolCode) {
        this.toolType = toolType;
        this.brand = brand;
        this.toolCode = toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public String getToolCode() {
        return toolCode;
    }
}
