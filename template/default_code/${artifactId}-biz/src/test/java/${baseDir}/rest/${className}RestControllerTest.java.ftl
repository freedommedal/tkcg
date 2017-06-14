package ${basePackage}.rest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgota.commons.dto.GeneralResponse;
import com.sgota.commons.dto.ListResponse;
import com.sgota.commons.dto.SingleResponse;

import ${basePackage}.AbstractBizTest;
import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}DeleteReq;
import ${basePackage}.api.request.${className}ListReq;
import ${basePackage}.api.request.${className}QueryReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.common.error.ErrorBase;

/**
 * test
 *
 * @author ${author}
 */
public class ${className}RestControllerTest extends AbstractBizTest {

    @Autowired
    private ${className}RestController api;

    @Test
    public void createTest() {
        ${className}CreateReq req = new ${className}CreateReq();
        GeneralResponse rsp = api.create(req);
        Assertions.assertThat(rsp.getCode()).isEqualTo(ErrorBase.SYSTEM_SUCCESS.code());
    }

    @Test
    public void deleteTest() {
        ${className}DeleteReq req = new ${className}DeleteReq();
        GeneralResponse rsp = api.delete(req);
        Assertions.assertThat(rsp.getCode()).isEqualTo(ErrorBase.SYSTEM_SUCCESS.code());
    }

    @Test
    public void updateTest() {
        ${className}UpdateReq req = new ${className}UpdateReq();
        GeneralResponse rsp = api.update(req);
        Assertions.assertThat(rsp.getCode()).isEqualTo(ErrorBase.SYSTEM_SUCCESS.code());
    }

    @Test
    public void queryTest() {
        ${className}QueryReq req = new ${className}QueryReq();
        req.setId(1L);
        SingleResponse<${className}QueryRsp> rsp = api.query(req);
        Assertions.assertThat(rsp.getCode()).isEqualTo(ErrorBase.SYSTEM_SUCCESS.code());
    }

    @Test
    public void listTest() {
        ${className}ListReq req = new ${className}ListReq();
        ListResponse<${className}QueryRsp> rsp = api.list(req);
        Assertions.assertThat(rsp.getCode()).isEqualTo(ErrorBase.SYSTEM_SUCCESS.code());
    }
}
