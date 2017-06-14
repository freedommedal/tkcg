package ${basePackage}.api;

import com.sgota.commons.dto.GeneralResponse;
import com.sgota.commons.dto.ListResponse;
import com.sgota.commons.dto.SingleResponse;

import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;

/**
 * Api
 *
 * @author ${author}
 */
public interface ${className}Api {

    /**
     * Create
     *
     * @param req req
     */
    GeneralResponse create(${className}CreateReq req);

    /**
     * Delete
     *
     * @param req req
     */
    GeneralResponse delete(${className}DeleteReq req);

    /**
     * Update
     *
     * @param req req
     */
    GeneralResponse update(${className}UpdateReq req);

    /**
     * Query
     *
     * @param req req
     * @return rep
     */
     SingleResponse<${className}QueryRsp> query(${className}QueryReq req);

    /**
     * List
     *
     * @param req req
     * @return rep
     */
     ListResponse<${className}QueryRsp> list(${className}ListReq req);
}
