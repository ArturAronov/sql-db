package org.db;

import java.nio.ByteBuffer;

public class Table {
    // Page is 4 kilobytes.
    private int numRows;
    private ByteBuffer[] pages;
    public static int ROWS_PER_PAGE;
    private static int TABLE_MAX_ROWS;
    private static final int PAGE_SIZE = 4096;
    private static final int TABLE_MAX_PAGES = 100;

    public Table(int numRows) {
        this.numRows = numRows;
        this.ROWS_PER_PAGE = PAGE_SIZE / numRows;
        this.TABLE_MAX_ROWS = this.ROWS_PER_PAGE * this.TABLE_MAX_PAGES;
    }

    public void rowSlot(int rowNum) {
        int pageNum = rowNum / this.ROWS_PER_PAGE;
        ByteBuffer page = pages[pageNum];
        if(page == null) {
            // Allocate memory only when we try to access page
            page = ByteBuffer.allocate(PAGE_SIZE);
            pages[pageNum] = page;
        }

        int rowOffset = rowNum % ROWS_PER_PAGE;
        int byteOffset = rowOffset * this.ROW_SIZE;

    }
}
