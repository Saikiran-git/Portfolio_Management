create table portfoliodetails (portfolioid int primary key);

create table assetdetails (id int primary key,portfolioid int,assetid varchar(20),assettype varchar(10),assetcount int, foreign key(portfolioid) references portfoliodetails(portfolioid));