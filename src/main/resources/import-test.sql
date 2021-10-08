-- ALTER TABLE PersonalizedFact ALTER COLUMN id SET DEFAULT uuid_in((md5((random())::text))::cstring);
ALTER TABLE PersonalizedFact ALTER COLUMN id SET DEFAULT random_uuid();
insert into PersonalizedFact(factId, text , type, source, sentCount, deleted, used, verified ) values('1281382123', 'true fact', 'cat', 'user', 2 , false, false, false);