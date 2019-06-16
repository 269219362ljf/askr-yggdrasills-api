create table socket_code_list(
id int  primary key auto_increment,
socket_code varchar(10) comment '股票代码',
socket_name varchar(50) comment '股票名称'
)
COMMENT '股票代码列表';