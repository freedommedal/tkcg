package ${basePackage}.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.common.util.PageHelper;
import ${basePackage}.convertor.${className}Convertor;
import ${basePackage}.convertor.${className}QueryRspConvertor;
import ${basePackage}.repository.dao.${className}Dao;
import ${basePackage}.repository.entity.${className};
import ${basePackage}.service.${className}Service;


/**
 * Service
 *
 * @author ${author}
 */
@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Dao ${className?uncap_first}Dao;

    @Override
    public void create(${className}CreateReq req) {
        ${className} po = new ${className}();
        ${className}Convertor.copy(req, po);
        po.setGmtCreate(new Date());
        po.setGmtModified(new Date());
        ${className?uncap_first}Dao.create(po);
    }

    @Override
    public void delete(${className}DeleteReq req) {
        Long id = req.getId();
        ${className} po = ${className?uncap_first}Dao.query(id);
        if (po == null) {
            return;
        }
        ${className?uncap_first}Dao.delete(id);
    }

    @Override
    public void update(${className}UpdateReq req) {
        Long id = req.getId();
        ${className} po = ${className?uncap_first}Dao.query(id);
        if (po == null) {
            return;
        }
        ${className}Convertor.copy(req, po);
        po.setGmtModified(new Date());
        ${className?uncap_first}Dao.update(po);
    }

    @Override
    public ${className}QueryRsp query(${className}QueryReq req) {
        Long id = req.getId();
        ${className} po = ${className?uncap_first}Dao.query(id);
        if (po == null) {
            return null;
        }
        ${className}QueryRsp rsp = new ${className}QueryRsp();
        ${className}QueryRspConvertor.copy(po, rsp);
        return rsp;
    }

    @Override
    public PageHelper<${className}QueryRsp> list(${className}ListReq req) {
        PageHelper pageHelper = new PageHelper(req.getPageNo(), req.getPageSize());
        int count = ${className?uncap_first}Dao.listCount();
        pageHelper.setCount(count);
        List<${className}QueryRsp> rsp;
        if (count > 0) {
            List<${className}> poList = ${className?uncap_first}Dao.list(pageHelper);
            rsp = ${className}QueryRspConvertor.convertList(poList);
        } else {
            rsp = Collections.emptyList();
        }
        pageHelper.setData(rsp);
        return pageHelper;
    }
}
