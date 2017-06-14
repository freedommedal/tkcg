package ${basePackage}.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页请求
 *
 * @author ${author}
 */
public class PageHelper<T extends Serializable> implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * 当前页.
     */
    private int pageNo = 1;
    /**
     * 每页数量.
     */
    private int pageSize = 10;
    /**
     * 返回值
     */
    private List<T> data;
    /**
     * 总数量
     */
    private int count = 0;

    /**
     * constructor
     */
    public PageHelper(){
    }

    /**
     * constructor
     *
     * @param pageNo the pageNo
     * @param pageSize the pageSize
     */
    public PageHelper(int pageNo, int pageSize){
        setPageNo(pageNo);
        setPageSize(pageSize);
    }

    /**
     * Gets page no.
     *
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets page no.
     *
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        if (pageNo > 1) {
            this.pageNo = pageNo;
        }
    }

    /**
     * Gets page size.
     *
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取偏移量.
     *
     * @return the offset
     */
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * Gets data
     *
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Sets data
     *
     * @param data the data
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * Gets count
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
