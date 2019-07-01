create table stock_code_list
(
    id         int primary key auto_increment,
    stock_code varchar(10) comment '股票代码',
    stock_name varchar(50) comment '股票名称',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
)
    COMMENT '股票代码列表';


CREATE TABLE `stock_transaction_data_list`
(
    `id`            int(11)   NOT NULL AUTO_INCREMENT,
    `stock_code`    varchar(10)        DEFAULT NULL COMMENT '股票代码',
    `trade`         decimal(9, 3)      DEFAULT NULL COMMENT '最新价',
    `pricechange`   decimal(9, 3)      DEFAULT NULL COMMENT '涨跌额',
    `changepercent` decimal(9, 3)      DEFAULT NULL COMMENT '涨跌幅',
    `buy`           decimal(9, 3)      DEFAULT NULL COMMENT '买入',
    `sell`          decimal(9, 3)      DEFAULT NULL COMMENT '卖出',
    `settlement`    decimal(9, 3)      DEFAULT NULL COMMENT '昨收',
    `open`          decimal(9, 3)      DEFAULT NULL COMMENT '今开',
    `high`          decimal(9, 3)      DEFAULT NULL COMMENT '最高',
    `low`           decimal(9, 3)      DEFAULT NULL COMMENT '最低',
    `volume`        bigint(20)         DEFAULT NULL COMMENT '成交量',
    `amount`        bigint(20)         DEFAULT NULL COMMENT '成效额',
    `created_at`    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    COMMENT ='股票交易数据表';



