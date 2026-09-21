public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int firstTenPages = Math.min(pages, 10);
        int extraPages = Math.max(pages - 10, 0);

        return (firstTenPages * 1500)
                + (extraPages * 1000)
                + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}