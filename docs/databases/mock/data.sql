
use learn;

drop table goods;
create table goods (
                       `id`  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
                       `cid` int(11) unsigned not null default 0,
                       `title` varchar(100),
                       `tags` json,
                       PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = 'article';

insert into goods(title, cid, tags) values
('article 1' , 1, '["123","112", "133"]'),
('article 2' , 1, '["123","112", "133"]'),
('article 3' , 1, '["123","112", "133"]'),
('article 4' , 1, '["123","112", "133"]'),

('article 5' , 2, '["123","456", "133"]'),
('article 6' , 2, '["123","456", "789"]'),
('article 7' , 2, '["123","456", "999"]'),

('article 100' , 3, '["123","112","133"]')
;

alter table goods add index tag( cid,  ( cast(tags->'$' as char array))  );


explain
select * from goods where cid=3 and   '133' member of (tags->'$');

explain
select * from goods where JSON_CONTAINS(tags->'$', '123') ;

explain
select * from goods where cid=1 and JSON_CONTAINS(tags->'$', '123') ;

explain
select * from goods where cid=1 and '123' member of (tags);

explain
select * from goods where cid=1;

