package enums;

public enum SearchResultSubcategory {
    APPLE("Apple");

    private String subcategory;

    SearchResultSubcategory(String subcategory){
        this.subcategory = subcategory;
    }

    public String getSubcategory(){
        return subcategory;
    }
}
