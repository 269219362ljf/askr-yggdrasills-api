package askr.model;

import lombok.Data;

import java.util.Date;

@Data
public class StocketApiListDataDto {
    //代码
    private String symbol;
    //名称
    private String name;
    //简码
    private String code;
    //最新价
    private Double trade;
    //涨跌额
    private Double pricechange;
    //涨跌幅
    private Double changepercent;
    //买入
    private Double buy;
    //卖出
    private Double sell;
    //昨收
    private Double settlement;
    //今开
    private Double open;
    //最高
    private Double high;
    //最低
    private Double low;
    //成交量
    private Long volume;
    //成效额
    private Long amount;
    //数据日期
    private Date dateTime;
}
