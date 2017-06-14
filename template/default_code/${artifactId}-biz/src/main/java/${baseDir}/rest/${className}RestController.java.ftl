package ${basePackage}.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgota.commons.dto.GeneralResponse;
import com.sgota.commons.dto.ListResponse;
import com.sgota.commons.dto.SingleResponse;

import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.common.util.PageHelper;
import ${basePackage}.service.${className}Service;

/**
 * Rest接口
 *
 * @author ${author}
 */
@RestController
@RequestMapping("/rest/${className?uncap_first}")
public class ${className}RestController {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @RequestMapping(path = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public GeneralResponse create(${className}CreateReq req) {
        ${className?uncap_first}Service.create(req);
        return new GeneralResponse();
    }

    @RequestMapping(path = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public GeneralResponse delete(${className}DeleteReq req) {
        ${className?uncap_first}Service.delete(req);
        return new GeneralResponse();
    }

    @RequestMapping(path = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public GeneralResponse update(${className}UpdateReq req) {
        ${className?uncap_first}Service.update(req);
        return new GeneralResponse();
    }

    @RequestMapping(path = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public SingleResponse<${className}QueryRsp> query(${className}QueryReq req) {
        ${className}QueryRsp rsp = ${className?uncap_first}Service.query(req);
        return new SingleResponse<>(rsp);
    }

    @RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ListResponse<${className}QueryRsp> list(${className}ListReq req) {
        PageHelper<${className}QueryRsp> rsp = ${className?uncap_first}Service.list(req);
        return new ListResponse<>(rsp.getData(), rsp.getCount());
    }
}
