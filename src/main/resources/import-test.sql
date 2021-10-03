-- ALTER TABLE PersonalizedFact ALTER COLUMN id SET DEFAULT uuid_in((md5((random())::text))::cstring);
ALTER TABLE PersonalizedFact ALTER COLUMN id SET DEFAULT random_uuid();
