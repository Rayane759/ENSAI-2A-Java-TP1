public abstract class Item {
    protected String title;
    protected int year;
    private int pageCount

    public Item(String title, int year, int pageCount) {
        this.title = title;
        this.year = year;
        this.pageCount = pageCount;
    }
}