package ${basePackage}.api.request;

import com.sgota.commons.dto.GeneralRequest;
import java.util.Date;
import java.math.BigDecimal;

/**
 * dto
 *
 * @author ${author}
 */
public class ${className}DeleteReq extends GeneralRequest {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * Gets id
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
