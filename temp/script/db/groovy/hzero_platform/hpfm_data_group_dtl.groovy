package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_data_group_dtl.groovy') {
    changeSet(author: "jianbo.li@hanf-china.com", id: "2019-07-25-hpfm_data_group_dtl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_data_group_dtl_s', startValue:"1")
        }
        createTable(tableName: "hpfm_data_group_dtl", remarks: "数据组明细定义") {
            column(name: "group_dtl_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "group_line_id", type: "bigint",  remarks: "数据组行ID")  {constraints(nullable:"false")}  
            column(name: "group_id", type: "bigint",  remarks: "数据组ID")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "dtl_value_id", type: "bigint",  remarks: "明细值ID，取自值集配置行ID")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"group_line_id,dtl_value_id",tableName:"hpfm_data_group_dtl",constraintName: "hpfm_data_group_dtl_u1")
    }
}