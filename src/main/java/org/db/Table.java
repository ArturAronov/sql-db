package org.db;

import java.nio.ByteBuffer;

public class Table {
    // Page is 4 kilobytes.
    private final int numRows;
    private final ByteBuffer[] pages;

    private static final int PAGE_SIZE = 4096;
    private static final int TABLE_MAX_PAGES = 100;

    private final int ROW_SIZE;
    private final int ROWS_PER_PAGE;
    private final int TABLE_MAX_ROWS;

    public Table(int numRows) {
        Serializer serializer = new Serializer();

        this.numRows = numRows;
        this.ROW_SIZE = serializer.getIdSize() + serializer.getEmailSize() + serializer.getUsernameSize();
        this.ROWS_PER_PAGE = PAGE_SIZE / this.ROW_SIZE;
        this.TABLE_MAX_ROWS = this.ROWS_PER_PAGE * TABLE_MAX_PAGES;

        this.pages = new ByteBuffer[TABLE_MAX_PAGES];
    }

    public ByteBuffer rowSlot(int rowNum) {
        int pageNum = rowNum / this.ROWS_PER_PAGE;
        ByteBuffer page = pages[pageNum];
        if(page == null) {
            // Allocate memory only when we try to access page
            page = ByteBuffer.allocate(PAGE_SIZE);
            pages[pageNum] = page;
        }

        int rowOffset = rowNum % ROWS_PER_PAGE;
        int byteOffset = rowOffset * ROW_SIZE;

        page.position(byteOffset);
        return page.slice();
    }
}
