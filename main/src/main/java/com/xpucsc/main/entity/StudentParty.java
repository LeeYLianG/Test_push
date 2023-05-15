package com.xpucsc.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 
 * @TableName student_party
 */
@TableName(value ="student_party")
@Data
@ApiModel("学生入党情况")
public class StudentParty implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 所属党支部
     */
    private String party;

    /**
     * 申请入党时间（提交入党申请书时间）
     */
    private String applicationTime;

    /**
     * 确认为入党积极分子时间
     */
    private String activistTime;

    /**
     * 确认为发展对象时间
     */
    private String developTime;

    /**
     * 转为正式党员时间
     */
    private String formalTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StudentParty other = (StudentParty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStuId() == null ? other.getStuId() == null : this.getStuId().equals(other.getStuId()))
            && (this.getParty() == null ? other.getParty() == null : this.getParty().equals(other.getParty()))
            && (this.getApplicationTime() == null ? other.getApplicationTime() == null : this.getApplicationTime().equals(other.getApplicationTime()))
            && (this.getActivistTime() == null ? other.getActivistTime() == null : this.getActivistTime().equals(other.getActivistTime()))
            && (this.getDevelopTime() == null ? other.getDevelopTime() == null : this.getDevelopTime().equals(other.getDevelopTime()))
            && (this.getFormalTime() == null ? other.getFormalTime() == null : this.getFormalTime().equals(other.getFormalTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStuId() == null) ? 0 : getStuId().hashCode());
        result = prime * result + ((getParty() == null) ? 0 : getParty().hashCode());
        result = prime * result + ((getApplicationTime() == null) ? 0 : getApplicationTime().hashCode());
        result = prime * result + ((getActivistTime() == null) ? 0 : getActivistTime().hashCode());
        result = prime * result + ((getDevelopTime() == null) ? 0 : getDevelopTime().hashCode());
        result = prime * result + ((getFormalTime() == null) ? 0 : getFormalTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuId=").append(stuId);
        sb.append(", party=").append(party);
        sb.append(", applicationTime=").append(applicationTime);
        sb.append(", activistTime=").append(activistTime);
        sb.append(", developTime=").append(developTime);
        sb.append(", formalTime=").append(formalTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}