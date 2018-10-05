package com.sgota.tkcg.core;

import java.util.List;

import com.sgota.tkcg.db.Column;
import com.sgota.tkcg.db.Table;

/**
 * @author tiankuo
 */
public class CodeModel {

    /**
     * The Code req
     */
    private CodeConfig codeReq;
    /**
     * The Table model
     */
    private Table tableModel;

    /**
     * Sets codeReq
     *
     * @param codeReq the codeReq
     */
    public void setCodeReq(CodeConfig codeReq) {
        this.codeReq = codeReq;
    }

    /**
     * Sets tableModel
     *
     * @param tableModel the tableModel
     */
    public void setTableModel(Table tableModel) {
        this.tableModel = tableModel;
    }

    public String getArtifactId() {
        return codeReq.getArtifactId();
    }

    public String getBasePackage() {
        return codeReq.getBasePackage();
    }

    public String getBaseDir() {return codeReq.getBaseDir();}

    public String getAuthor() {
        return codeReq.getAuthor();
    }

    public String getTableName() {
        return tableModel.getTableName();
    }

    public String getClassName() {
        return tableModel.getClassName();
    }

    public String getPrimaryColumn() {
        return tableModel.getPrimaryColumn();
    }

    public String getPrimaryName() {
        return tableModel.getPrimaryName();
    }

    public Boolean getPrimaryAuto() {
        return tableModel.getPrimaryAuto();
    }

    public List<Column> getColumns() {
        return tableModel.getColumns();
    }
}
