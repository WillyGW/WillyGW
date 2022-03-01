create table if not exists fund(
	fid integer not null auto_increment,
	fname varchar(50),
	createtime datetime default current_timestamp,
	primary key (fid)
);

create table if not exists fundstock(
	sid integer not null auto_increment,
	fid integer,
	symbol varchar(50),
	share integer,
	primary key(sid),
	foreign key(fid) references fund(fid)
);

insert into fund(fname) values ('A');
insert into fund(fname) values ('B');
insert into fund(fname) values ('C');
insert into fund(fname) values ('D');
insert into fund(fname) values ('E');
insert into fund(fname) values ('F');
insert into fund(fname) values ('G');

insert into fundstock(fid, symbol, share) values (1, '2330.TW', 50000);
insert into fundstock(fid, symbol, share) values (2, '2330.TW', 60000);
insert into fundstock(fid, symbol, share) values (2, '2303.TW', 70000);
insert into fundstock(fid, symbol, share) values (3, '2376.TW', 80000);
insert into fundstock(fid, symbol, share) values (3, '1101.TW', 90000);
insert into fundstock(fid, symbol, share) values (3, '2317.TW', 80000);
insert into fundstock(fid, symbol, share) values (4, '3231.TW', 70000);
insert into fundstock(fid, symbol, share) values (4, '2356.TW', 60000);
insert into fundstock(fid, symbol, share) values (4, '2880.TW', 50000);
insert into fundstock(fid, symbol, share) values (4, '2891.TW', 40000);
insert into fundstock(fid, symbol, share) values (5, '2330.TW', 30000);
insert into fundstock(fid, symbol, share) values (5, '1101.TW', 20000);
insert into fundstock(fid, symbol, share) values (5, '2317.TW', 10000);
insert into fundstock(fid, symbol, share) values (5, '2886.TW', 20000);
insert into fundstock(fid, symbol, share) values (5, '2002.TW', 30000);

insert into fundstock (symbol) values("2480.TW");
insert into fundstock (symbol) values("1201.TW");
insert into fundstock (symbol) values("1216.TW");

select s.sid, s.fid, s.symbol, s.share , 
f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  
from fundstock s left join fund f on f.fid = s.fid
order by s.sid
limit 5
offset 10
