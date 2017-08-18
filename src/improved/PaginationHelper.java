package improved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PaginationHelper<I> {

    int listSize;
    int pageSize;
    int pageCount;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        System.out.println(collection.stream().map(Object::toString).collect(Collectors.joining(", ")));
        listSize = collection.size();
        pageSize = itemsPerPage;
        pageCount = Math.round(listSize / pageSize) ;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return listSize;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pageCount + 1;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        System.out.println("pi :" + pageIndex);
        return pageIndex >= 0 && pageIndex <= pageCount ?
                pageIndex < pageCount ?
                        pageSize : listSize % pageSize
                : -1;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        System.out.println("ii :" + itemIndex);
        int val = itemIndex > 0 && itemIndex < listSize ?
                Math.round(itemIndex / pageSize) + 1: -1;
        return val;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        PaginationHelper<Integer> ph = new PaginationHelper<>(list, 10);
        System.out.println(ph.pageIndex(40));
        System.out.println(ph.pageIndex(3));


    }
}

