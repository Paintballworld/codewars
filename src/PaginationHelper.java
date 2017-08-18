import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class PaginationHelper<I> {

    private Map<Integer, List<I>> pager = new HashMap<>();
    int pageSize;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        AtomicInteger ai = new AtomicInteger(0);
        collection.forEach(i -> {
            pager.computeIfAbsent(ai.get(), k -> new ArrayList<>()).add(i);
            if (pager.get(ai.get()).size() == itemsPerPage)
                ai.incrementAndGet();
        });
        pageSize = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return Math.toIntExact(pager.values().stream().flatMap(List::stream).count());
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pager.size();
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        return pager.get(pageIndex) == null ? -1 : pager.get(pageIndex).size();
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        return pageSize == 0 ||
                itemIndex > itemCount() ||
                itemIndex < 0 ||
                pager.size() == 0 ?
                -1 : itemCount() / pageSize;
    }
}

