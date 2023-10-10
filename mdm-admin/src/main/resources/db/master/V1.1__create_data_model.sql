create table data_model
(
    id          bigint(20) not null auto_increment comment '主数据模型id',
    name        varchar(50) not null comment '模型名称',
    code        varchar(50) comment '模型编码',
    description varchar(200) comment '描述',
    status      varchar(20) not null comment '状态',
    version     int comment '版本号',
    enabled     boolean     not null default true comment '是否启用',
    sort_index  int         not null default 0 comment '排序值',
    create_by   varchar(64)          default '' comment '创建者',
    create_time datetime    not null default current_timestamp() comment '创建时间',
    update_by   varchar(64)          default '' comment '更新者',
    update_time datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '主数据模型表';

create table view
(
    id            bigint(20) not null auto_increment comment 'id',
    name          varchar(50) not null comment '视图标题',
    code          varchar(50) comment '视图代号',
    description   varchar(200) comment '描述',
    table_name    varchar(50) comment '数据库表名',
    version       int comment '版本号',
    data_model_id bigint(20) comment '主数据模型ID',
    enabled       boolean     not null default true comment '是否启用',
    sort_index    int         not null default 0 comment '排序值',
    create_by     varchar(64)          default '' comment '创建者',
    create_time   datetime    not null default current_timestamp() comment '创建时间',
    update_by     varchar(64)          default '' comment '更新者',
    update_time   datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '视图表';

create table view_field
(
    id            bigint(20) not null auto_increment comment 'id',
    col_name      varchar(50) not null comment '列名',
    name          varchar(50) not null comment '标题',
    data_type     varchar(50) not null comment '数据类型',
    length        int comment '长度',
    prec          int comment '精度',
    nullable      boolean     not null default true comment '是否可空',
    uniqueness    boolean     not null default false comment '是否唯一',
    default_value varchar(100) comment '默认值',
    dimension_id  bigint(20) comment '参考数据ID',
    field_rule_id bigint(20) comment '编码规则ID',
    view_id       bigint(20) comment '主数据模型ID',
    sort_index    int         not null default 0 comment '排序值',
    create_by     varchar(64)          default '' comment '创建者',
    create_time   datetime    not null default current_timestamp() comment '创建时间',
    update_by     varchar(64)          default '' comment '更新者',
    update_time   datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '视图字段表';

create table field_rule
(
    id          bigint(20) not null auto_increment comment 'id',
    name        varchar(50) not null comment '规则名称',
    description varchar(200) comment '描述',
    status      varchar(20) not null comment '状态',
    enabled     boolean     not null default true comment '是否启用',
    create_by   varchar(64)          default '' comment '创建者',
    create_time datetime    not null default current_timestamp() comment '创建时间',
    update_by   varchar(64)          default '' comment '更新者',
    update_time datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '编码规则表';

create table field_rule_detail
(
    id          bigint(20) not null auto_increment comment 'id',
    rule_id     bigint(20) not null comment '编码规则ID',
    code        varchar(50) comment '编号',
    type        varchar(20) not null comment '码段类型',
    rule        json comment '规则详情',
    create_by   varchar(64)          default '' comment '创建者',
    create_time datetime    not null default current_timestamp() comment '创建时间',
    update_by   varchar(64)          default '' comment '更新者',
    update_time datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '编码规则设置表';

create table dimension
(
    id             bigint(20) not null auto_increment comment 'id',
    name           varchar(50) not null comment '名称',
    table_name     varchar(50) comment '表名',
    id_field_name  varchar(50) comment 'ID字段',
    str_field_name varchar(50) comment '文本字段',
    status         varchar(20) not null comment '状态',
    enabled        boolean     not null default true comment '是否启用',
    create_by      varchar(64)          default '' comment '创建者',
    create_time    datetime    not null default current_timestamp() comment '创建时间',
    update_by      varchar(64)          default '' comment '更新者',
    update_time    datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '维表';

create table dimension_field
(
    id           bigint(20) not null auto_increment comment 'id',
    name         varchar(50) not null comment '名称',
    col_name     varchar(50) not null comment '列名',
    data_type    varchar(50) not null comment '字段类型',
    length       int comment '长度',
    prec         int comment '精度',
    dimension_id bigint(20) not null comment '维表ID',
    parent_id    bigint(20) not null comment '上级列名ID',
    create_by    varchar(64)          default '' comment '创建者',
    create_time  datetime    not null default current_timestamp() comment '创建时间',
    update_by    varchar(64)          default '' comment '更新者',
    update_time  datetime    not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '维表字段';
