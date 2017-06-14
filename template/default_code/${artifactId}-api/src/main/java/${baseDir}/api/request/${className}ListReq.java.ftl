package ${basePackage}.api.request;

import com.sgota.commons.dto.GeneralRequest;
import java.util.Date;
import java.math.BigDecimal;

/**
 * dto
 *
 * @author ${author}
 */
public class ${className}ListReq extends GeneralRequest {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * 当前页.
     */
    private int pageNo;
    /**
     * 每页数量.
     */
    private int pageSize;

    /**
     * Gets pageNo
     *
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets pageNo
     *
     * @param pageNo the pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * Gets pageSize
     *
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets pageSize
     *
     * @param pageSize the pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
