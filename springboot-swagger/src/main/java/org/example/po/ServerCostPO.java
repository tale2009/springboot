package org.example.po;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
@ApiModel("ServerCost对象")
public class ServerCostPO implements Serializable
{
    @ApiModelProperty("cost名称")
    @NotNull(message="cost名称不能为空")
    private String costName;
    @ApiModelProperty("cost值")
    
    private BigDecimal costValue;
    @ApiModelProperty("最后一次更新")
    @NotNull(message="最后一次更新不能为空")
    private Date lastUpdate;
    @ApiModelProperty("commet")
    
    private String comment;
    @ApiModelProperty("默认值")
    
    private BigDecimal defaultValue;

    public String getCostName()
    {
        return this.costName;
    }

    public void setCostName(String costName)
    {
    this.costName=costName;
    }

    public BigDecimal getCostValue()
    {
        return this.costValue;
    }

    public void setCostValue(BigDecimal costValue)
    {
    this.costValue=costValue;
    }

    public Date getLastUpdate()
    {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
    this.lastUpdate=lastUpdate;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
    this.comment=comment;
    }

    public BigDecimal getDefaultValue()
    {
        return this.defaultValue;
    }

    public void setDefaultValue(BigDecimal defaultValue)
    {
    this.defaultValue=defaultValue;
    }

}
