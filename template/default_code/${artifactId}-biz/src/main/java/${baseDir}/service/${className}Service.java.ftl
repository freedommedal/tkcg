package ${basePackage}.service;

import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.common.util.PageHelper;

/**
 * Service
 *
 * @author ${author}
 */
public interface ${className}Service {

    /**
     * Create
     *
     * @param req req
     */
    void create(${className}CreateReq req);

    /**
     * Delete
     *
     * @param req req
     */
    void delete(${className}DeleteReq req);

    /**
     * Update
     *
     * @param req req
     */
    void update(${className}UpdateReq req);

    /**
     * Query
     *
     * @param req req
     * @return rsp
     */
    ${className}QueryRsp query(${className}QueryReq req);

    /**
     * List
     *
     * @param req req
     * @return rsp
     */
    PageHelper<${className}QueryRsp> list(${className}ListReq req);
}