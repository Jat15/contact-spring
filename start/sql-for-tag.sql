USE contact;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (1,'mère','père','parent',NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (2,'fille','fils','enfant',1);
UPDATE tag SET tag_dest_id = 2 WHERE id = 1;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (3,'soeur','frère',NULL,NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (4,'grand-mère','grand-père','grand-parent',NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (5,'petite-fille','petit-fils','petit-enfant',4);
UPDATE tag SET tag_dest_id = 5 WHERE id = 4;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (6,'tante','oncle',NULL,NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (7,'niéce','neveu',NULL,6);
UPDATE tag SET tag_dest_id = 7 WHERE id = 6;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (8,'cousine','cousin',NULL,NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (9,'belle-mère','beau-père','beau-parent',NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (10,'belle-fille','beau-fils',NULL,9);
UPDATE tag SET tag_dest_id = 10 WHERE id = 9;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (11,'grande-tante','grand-oncle',NULL,NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (12,'peite-nièce','petit-neveu',NULL,11);
UPDATE tag SET tag_dest_id = 12 WHERE id = 11;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (13,'arrière-grand-mère','arrière-grand-père','arrière-grand-parent',NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (14,'arrière-petite-fille','arrière-petit-fils','arrière-petit-enfant',13);
UPDATE tag SET tag_dest_id = 13 WHERE id = 12;
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (15,'marraine','parrain',NULL,NULL);
INSERT INTO tag(id,female,male,neutral,tag_dest_id) VALUES (16,'filleule','filleul',NULL,15);
UPDATE tag SET tag_dest_id = 16 WHERE id = 15;