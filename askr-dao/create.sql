create table stock_code_list(
id int  primary key auto_increment,
stock_code varchar(10) comment '股票代码',
stock_name varchar(50) comment '股票名称',
created_at timestamp not null default CURRENT_TIMESTAMP ,
updated_at timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
)
COMMENT '股票代码列表';