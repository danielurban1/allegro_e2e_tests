package enums;

public enum PhoneColor {
    BLACK("czarny");

    private String color;

    PhoneColor(String color){
        this.color = color;
    }

    public String getColorValue(){
        return color;
    }
}
