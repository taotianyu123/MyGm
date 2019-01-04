package pojo;

import java.util.List;

/**
 * @ClassName pojo.Page
 * @Author tty
 * @Date 2018\12\20 0020 15:32
 * @Version 1.0
 */
public class Page<T> {
    private int pageSize;
    private int pageNumber;
    private int totalRecode;
    private List<T> PageDate;


    public Page() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalRecode() {
        return totalRecode;
    }

    public void setTotalRecode(int totalRecode) {
        this.totalRecode = totalRecode;
    }

    public List<T> getPageDate() {
        return PageDate;
    }

    public void setPageDate(List<T> pageDate) {
        PageDate = pageDate;
    }

    public Page(int pageSize, int pageNumber, int totalRecode, List<T> pageDate) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalRecode = totalRecode;
        this.PageDate = pageDate;
    }
    public int getTotalPage(){
        return totalRecode%pageSize==0?totalRecode/pageSize :totalRecode/pageSize+1;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", totalRecode=" + totalRecode +
                ", PageDate=" + PageDate +
                '}';
    }
}
