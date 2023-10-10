create table dimension_instance
(
    id           bigint(20) not null auto_increment comment 'id',
    key_data     varchar(50)  not null comment 'key',
    value_data   varchar(200) not null comment 'value',
    parent_id    bigint(20) not null comment '父级ID',
    status       varchar(20)  not null comment '状态',
    sort_index   int          not null default 0 comment '排序值',
    is_show      boolean      not null default true comment '是否显示',
    dimension_id bigint(20) not null comment '维表ID',
    create_by    varchar(64)           default '' comment '创建者',
    create_time  datetime     not null default current_timestamp() comment '创建时间',
    update_by    varchar(64)           default '' comment '更新者',
    update_time  datetime     not null default current_timestamp() comment '更新时间',
    primary key (id)
) engine=innodb comment = '维表实例数据';