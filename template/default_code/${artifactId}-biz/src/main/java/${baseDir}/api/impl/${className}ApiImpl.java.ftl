package ${basePackage}.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgota.commons.dto.GeneralResponse;
import com.sgota.commons.dto.ListResponse;
import com.sgota.commons.dto.SingleResponse;

import ${basePackage}.api.${className}Api;
import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.common.util.PageHelper;
import ${basePackage}.service.${className}Service;

/**
 * Api
 *
 * @author ${author}
 */
@Service
public class ${className}ApiImpl implements ${className}Api {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @Override
    public GeneralResponse create(${className}CreateReq req) {
        ${className?uncap_first}Service.create(req);
        return new GeneralResponse();
    }

    @Override
    public GeneralResponse delete(${className}DeleteReq req) {
        ${className?uncap_first}Service.delete(req);
        return new GeneralResponse();
    }

    @Override
    public GeneralResponse update(${className}UpdateReq req) {
        ${className?uncap_first}Service.update(req);
        return new GeneralResponse();
    }

    @Override
    public SingleResponse<${className}QueryRsp> query(${className}QueryReq req) {
        ${className}QueryRsp rep = ${className?uncap_first}Service.query(req);
        return new SingleResponse<>(rep);
    }

    @Override
    public ListResponse<${className}QueryRsp> list(${className}ListReq req) {
        PageHelper<${className}QueryRsp> rep = ${className?uncap_first}Service.list(req);
        return new ListResponse<>(rep.getData(), rep.getCount());
    }
}
