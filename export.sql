--------------------------------------------------------
--  File created - Friday-February-14-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type VARCHAR2_ARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "EBOOKS"."VARCHAR2_ARRAY" 
AS VARRAY(200) OF VARCHAR2(400);

/
--------------------------------------------------------
--  DDL for Sequence BOOKS_GENRES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."BOOKS_GENRES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 921 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BOOKS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."BOOKS_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 741 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BOOK_TYPES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."BOOK_TYPES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 22 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence GENRES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."GENRES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 31 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence JOBS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."JOBS_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 161 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LANGUAGES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."LANGUAGES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 55 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ROLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."ROLES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SHARED_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."SHARED_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SHARED_TYPES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."SHARED_TYPES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_GENRES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."USERS_GENRES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EBOOKS"."USERS_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 22 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table BOOKS
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."BOOKS" 
   (	"BOOK_ID" NUMBER(6,0), 
	"AUTHOR" VARCHAR2(4000 BYTE), 
	"TITLE" VARCHAR2(4000 BYTE), 
	"PUB_DATE" DATE, 
	"FILE_NAME" VARCHAR2(4000 BYTE), 
	"BOOK_SIZE" NUMBER(10,0), 
	"PATH" VARCHAR2(4000 BYTE), 
	"SHA1" VARCHAR2(4000 BYTE), 
	"ID_OWNER" NUMBER(6,0), 
	"ID_LANG" NUMBER(6,0), 
	"ID_BOOK_TYPE" NUMBER(6,0), 
	"ANNOTATION" VARCHAR2(4000 BYTE), 
	"ISBN" VARCHAR2(4000 BYTE), 
	"ID_SHARED_TYPE" NUMBER(6,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BOOKS_GENRES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."BOOKS_GENRES" 
   (	"BOOKS_GENRES_ID" NUMBER(6,0), 
	"BOOK_ID" NUMBER(6,0), 
	"GENRE_ID" NUMBER(6,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BOOK_TYPES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."BOOK_TYPES" 
   (	"BOOK_TYPE_ID" NUMBER(6,0), 
	"BOOK_TYPE_NAME" VARCHAR2(4000 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GENRES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."GENRES" 
   (	"GENRE_ID" NUMBER(6,0), 
	"GENRE_NAME" VARCHAR2(4000 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table JOBS
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."JOBS" 
   (	"JOB_ID" NUMBER(6,0), 
	"USER_ID" NUMBER(6,0), 
	"JOB_COMMENT" VARCHAR2(4000 BYTE), 
	"START_TIME" DATE, 
	"END_TIME" DATE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LANGUAGES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."LANGUAGES" 
   (	"LANGUAGE_ID" NUMBER(6,0), 
	"LANGUAGE_NAME" VARCHAR2(4000 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."ROLES" 
   (	"ROLE_ID" NUMBER(6,0), 
	"ROLE_NAME" VARCHAR2(4000 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SHARED
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."SHARED" 
   (	"SHARED_ID" NUMBER(6,0), 
	"ID_BOOK" NUMBER(6,0), 
	"ID_OWNER" NUMBER(6,0), 
	"ID_GRANTEE" NUMBER(6,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SHARED_TYPES
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."SHARED_TYPES" 
   (	"SHARED_TYPE_ID" NUMBER(6,0), 
	"SHARED_TYPE_NAME" VARCHAR2(4000 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "EBOOKS"."USERS" 
   (	"USER_ID" NUMBER(6,0), 
	"EMAIL" VARCHAR2(4000 BYTE), 
	"PASSWORD" VARCHAR2(4000 BYTE), 
	"FIRST_NAME" VARCHAR2(4000 BYTE), 
	"LAST_NAME" VARCHAR2(4000 BYTE), 
	"REGISTRATION_DATE" DATE, 
	"ROLE_ID" NUMBER(6,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into EBOOKS.BOOKS
SET DEFINE OFF;
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (717,'Richard Curtis','Mr Bean in Town',to_date('13-FEB-14','DD-MON-RR'),'Mr_Bean_in_Town-Richard_Curtis.fb2',132636,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','61304045',1,37,1,'
                Mr. Bean decided to go to the restaurant on his Birthday, because he wanted to enjoy his celebration. But to be at the restaurants was unusual situation for him. And our hero demonstrated his strange behavior there. People didn’t understand him. He hadn’t enough money for the order, so he choosed “steak tartare”. He didn’t like this food and big plate for food had bad impression on him. Mr. Bean didn’t know what to do with it. And had found a great decision to hide the meat in things surrounding him. He considered himself very smart in this situation. Than he showed to waiter this dirty place and manager took him a new table and he had a new embarrassing position… Later our hero had to clean his dress and went to the to the launderette. In the launderette he has found a new adventures…
            ','8-6598-1296-6',2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (716,'Thomas Hardy','Tess Of dUrberville',to_date('13-FEB-14','DD-MON-RR'),'Tess_Of_Durberville-Thomas_Hardy.fb2',163265,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','1348461825',1,37,1,'
                Once Jack – the head of the Durbeyfield family, accidentally reveals that his family is descended from the ancient knightly family d’Urberville. Jack goes to pub to celebrate the gentility of his family. There he learns that some Mr. Stoke-d’Urberville living not far away. The next day on the morning Jack cannot wake up, so Tess had to drive products with the help of their only value possession – the horse. Tess Young and her little brother went to the town. But unluckily they fall asleep while were driving and their horse crashed into a wood and died. Mother insists that Tess should go to the Stoke-d’Urberville. She hopes he might help them as realities. But indeed he hasn’t any connection with the d’Urbervilles, he only took that name from local history book. But he really liked the young Tess and he is not very shy to show his fondness.
            ','9-7690-4800-8',2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (720,'George Martin','A Dance With Dragons',to_date('13-FEB-14','DD-MON-RR'),'dance_with_dragons.fb2',2937087,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','1336808162',1,37,1,'
    In the aftermath of a colossal battle, the future of the Seven Kingdoms hangs in the balance—beset by newly emerging threats from every direction. In the east, Daenerys Targaryen, the last scion of House Targaryen, rules with her three dragons as queen of a city built on dust and death. But Daenerys has thousands of enemies, and many have set out to find her. As they gather, one young man embarks upon his own quest for the queen, with an entirely different goal in mind.
    Fleeing from Westeros with a price on his head, Tyrion Lannister, too, is making his way to Daenerys. But his newest allies in this quest are not the rag-tag band they seem, and at their heart lies one who could undo Daenerys’s claim to Westeros forever.
    Meanwhile, to the north lies the mammoth Wall of ice and stone—a structure only as strong as those guarding it. There, Jon Snow, 998th Lord Commander of the Night’s Watch, will face his greatest challenge. For he has powerful foes not only within the Watch but also beyond, in the land of the creatures of ice.
    From all corners, bitter conflicts reignite, intimate betrayals are perpetrated, and a grand cast of outlaws and priests, soldiers and skinchangers, nobles and slaves, will face seemingly insurmountable obstacles. Some will fail, others will grow in the strength of darkness. But in a time of rising restlessness, the tides of destiny and politics will lead inevitably to the greatest dance of all.
   ','978-0007442454',1);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (715,'Richard Curtis','Four Weddings And A Funeral',to_date('13-FEB-14','DD-MON-RR'),'Four_Weddings_And_A_Funeral-Richard_Curtis.fb2',171887,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','-928563826',1,37,1,'
                There are friends and we can see the story through the eyes of Charles. Charles is a shy Englishman he can do strange things. He charmed by Carry, a beautiful American. They always meet at the weddings and funerals. The first wedding was between Angus and Lora. There unmarried friends have begun worried about they would stay single. At this wedding Charles has met Carry in first time and spent night with her. The young woman will hope that her lover declare about their wedding. But he didn’t do that because it is one night stands relations for him. The second wedding are celebrating Bernard and Lidia. Their relations have begun at the first wedding. At this bridal Charles has known that Carry engaged with the rich scotland politician. And Charles spent with her one more night.
            ','5-9713-1191-3',2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (719,'Wilkie Collins','Woman in white',to_date('13-FEB-14','DD-MON-RR'),'Woman_in_white-Wilkie_Collins.fb2',110166,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','-640389868',1,37,1,'
                The young painter Walter Hartright has got a job like a drawing teacher in the Limmeridge. He was recommended at this position by an Italian professor Pesca, his good friend. Walter was living in London, so he has to leave his town and went to Cumberland for this work. He decided to say goodbye with his mother and sister. When he went out he met a strange woman. She was dressed in white from head to foot. They continued their way together and were talking. The woman in white has excited when he told about his future work, about people had hired him. And she has been telling about these people with love, anger and fear. Then Walter helped her to catch the cab and almost right he met two men who were searching woman in white which had escaped from the asylum.
            ','3-6580-2377-8',1);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (721,'George Martin','A Feast for Crows',to_date('13-FEB-14','DD-MON-RR'),'feast_for_crows.fb2',2166085,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','1463393788',1,37,1,'
        Long-awaited doesn\''t begin to describe this fourth installment in bestseller Martin\''s staggeringly epic Song of Ice and Fire. Speculation has run rampant since the previous entry, A Storm of Swords, appeared in 2000, and Feast teases at the important questions but offers few solid answers. As the book begins, Brienne of Tarth is looking for Lady Catelyn\''s daughters, Queen Cersei is losing her mind and Arya Stark is training with the Faceless Men of Braavos; all three wind up in cliffhangers that would do justice to any soap opera. Meanwhile, other familiar faces¿notably Jon Snow, Tyrion Lannister and Daenerys Targaryen¿are glaringly absent though promised to return in book five. Martin\''s Web site explains that Feast and the forthcoming A Dance of Dragons were written as one book and split after they grew too big for one volume, and it shows. This is not Act I Scene 4 but Act II Scene 1, laying groundwork more than advancing the plot, and it sorely misses its other half. The slim pickings here are tasty, but in no way satisfying.
      ','0553801503',1);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (722,'George Martin','A Clash of Kings',to_date('13-FEB-14','DD-MON-RR'),'clash_of_kings.fb2',2197183,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','1190695232',1,37,1,'
        The Seven Kingdoms have come apart. Joffrey, Queen Cersei''s sadistic son, ascends the Iron Throne following the death of Robert Baratheon, the Usurper, who won it in battle. Queen Cersei''s family, the Lannisters, fight to hold it for him. Both the dour Stannis and the charismatic Renly Baratheon, Robert''s brothers, also seek the throne. Robb Stark, declared King in the North, battles to avenge his father''s execution and retrieve his sister from Joffrey''s court. Daenerys, the exiled last heir of the former ruling family, nurtures three dragons and seeks a way home. Meanwhile the Night''s Watch, sworn to protect the realm from dangers north of the Wall, dwindle in numbers, even as barbarian forces gather and beings out of legend stalk the Haunted Forest.
      ',null,2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (723,'George Martin','A game of thrones',to_date('13-FEB-14','DD-MON-RR'),'game_of_thrones.fb2',1958837,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','-295659626',1,37,1,'
        In a world where the approaching winter will last four decades, kings and queens, knights and renegades struggle for control of a throne. Some fight with sword and mace, others with magic and poison. Beyond the Wall to the north, meanwhile, the Others are preparing their army of the dead to march south as the warmth of summer drains from the land. After more than a decade devoted primarily to TV and screen work, Martin (The Armageddon Rag, 1983) makes a triumphant return to high fantasy with this extraordinarily rich new novel, the first of a trilogy. Although conventional in form, the book stands out from similar work by Eddings, Brooks and others by virtue of its superbly developed characters, accomplished prose and sheer bloody-mindedness. Although the romance of chivalry is central to the culture of the Seven Kingdoms, and tournaments, derring-do and handsome knights abound, these trappings merely give cover to dangerous men and women who will stop at nothing to achieve their goals. When Lord Stark of Winterfell, an honest man, comes south to act as the King''s chief councilor, no amount of heroism or good intentions can keep the realm under control. It is fascinating to watch Martin''s characters mature and grow, particularly Stark''s children, who stand at the center of the book. Martin''s trophy case is already stuffed with major prizes, including Hugos, Nebulas, Locus Awards and a Bram Stoker. He''s probably going to have to add another shelf, at least. Major ad/promo.
      ',null,1);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (724,'George Martin','A Storm of Swords',to_date('13-FEB-14','DD-MON-RR'),'storm_of_swords.fb2',2654217,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','-1473778794',1,37,1,'
        The third volume of the high fantasy saga that began with A Game of Thrones and continued in A Clash of Kings is one of the more rewarding examples of gigantism in contemporary fantasy. As Martin''s richly imagined world slides closer to its 10-year winter, both the weather and the warfare worsen. In the north, King Joffrey of House Lannister sits uneasily on the Iron Throne. With the aid of a peasant wench, Jaime Lannister, the Kingslayer, escapes from jail in Riverrun. Jaime goes to the other youthful ruler, Robb Stark, to secure the release of Joffrey''s prisoners, Robb''s sisters Arya and Sansa Stark. Meanwhile, in the south, Queen Daenarys tries to assert her claim to the various thrones with an army of eunuchs, but discovers that she must choose between conquering more and ruling well what she has already taken. The complexity of characters such as Daenarys, Arya and the Kingslayer will keep readers turning even the vast number of pages contained in this volume, for the author, like Tolkien or Jordan, makes us care about their fates. Those two fantasy greats are also evoked by Martin''s ability to convey such sensual experiences as the heat of wildfire, the chill of ice, the smell of the sea and the sheer gargantuan indigestibility of the medieval banquet at its most excessive. Perhaps this saga doesn''t go as far beyond the previous bounds of high fantasy as some claim, but for most readers it certainly goes far enough to command their attention.
      ',null,2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (718,'Richard MacAndrew','Strong Medicine',to_date('13-FEB-14','DD-MON-RR'),'Strong_Medicine-Richard_MacAndrew.fb2',125537,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','-578623039',1,37,1,'
                Dr. Mark Latto came from England to America on the Santa Cruz Island. He has arrived here to meet with Deborah Spencer. Deborah like and Mark was a doctor and she was surprised that the doctor of the Western medicine have been interested of her work. Debora was working on a new method of treating Parkinson’s disease and Latto has interested her nonstandard methods because Western medicine could do nothing to help patients with this disease. When Mark came to her house he have been notified by the Police that Debora was dead. She had a heart attack. Mark decided to stay in Santa Kruz to know much information about Deborah’s work and her death. The next day, when he came back to Deborah’s house to pick up his glasses he met there a young man. This young man hit him on the head and Mark fainted. Later Mark have been said from the Deborah’s collegues that she had written a book about her method of treating Parkinson’s disease. But this book was in one copy in her laptop. This laptop disappeared after her death. And Mark has remembered, that the man who hit him has a laptop on his hands.
            ','5-9578-3406-4',2);
Insert into EBOOKS.BOOKS (BOOK_ID,AUTHOR,TITLE,PUB_DATE,FILE_NAME,BOOK_SIZE,PATH,SHA1,ID_OWNER,ID_LANG,ID_BOOK_TYPE,ANNOTATION,ISBN,ID_SHARED_TYPE) values (714,'Joss Whedon','Inca Mummy Girl',to_date('13-FEB-14','DD-MON-RR'),'Inca_Mummy_Girl-Joss_Whedon.fb2',97971,'D:/EBOOKS_STORAGE/MAIN_CATALOGUE/','913954622',1,37,1,'
                It was sunny day. Baffy’s school went to the museum. Buffy, Willow and Xander were talking about upcoming events by the way. Students of the exchange programme come for them soon. Buffy didn’t interested who it would guy or girl. Because she didn’t want guests in her house at all. Xander loves Buffy and is jealous of her if this student will muscular guy. At that time, guide has begun talking the legend about the princess of Incan. Incan had choosen the new beautiful young princess and immolated her for the mountain god 500 years ago. Princess were guarded by the seal. It was the warning for everyone who wants to wake her up. The sacrifice was buried alive forever and remained in the tomb. Our trinity were looking at this mummy of princess and compassion on her. Soon Xander has recollected about exchange of students and all left the room. But one of Buffy’s schoolmates has came to the mummy and accidentally he has woken up the dead princess…
            ','5-17-033859-7',1);
REM INSERTING into EBOOKS.BOOKS_GENRES
SET DEFINE OFF;
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (893,718,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (895,716,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (892,714,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (897,719,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (898,719,3);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (894,715,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (896,717,11);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (899,719,5);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (900,720,15);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (901,721,14);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (902,722,14);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (903,723,14);
Insert into EBOOKS.BOOKS_GENRES (BOOKS_GENRES_ID,BOOK_ID,GENRE_ID) values (904,724,14);
REM INSERTING into EBOOKS.BOOK_TYPES
SET DEFINE OFF;
Insert into EBOOKS.BOOK_TYPES (BOOK_TYPE_ID,BOOK_TYPE_NAME) values (1,'fb2');
REM INSERTING into EBOOKS.GENRES
SET DEFINE OFF;
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (7,'adventure');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (11,'antique');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (3,'comedy');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (1,'crime');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (2,'detective');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (4,'fantasy');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (9,'gothic horror');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (5,'horror');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (6,'novel');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (12,'sf_detective');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (14,'sf_epic');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (15,'sf_fantasy');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (13,'sf_horror');
Insert into EBOOKS.GENRES (GENRE_ID,GENRE_NAME) values (8,'thriller');
REM INSERTING into EBOOKS.JOBS
SET DEFINE OFF;
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (124,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (127,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (133,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (134,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (135,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (136,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (137,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (138,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (139,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (141,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (142,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (144,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (145,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (147,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (103,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (107,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (108,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (111,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (112,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (116,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (119,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (146,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (150,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (104,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (115,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (118,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (120,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (121,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (125,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (131,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (102,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (114,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (117,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (122,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (123,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (128,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (132,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (140,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (110,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (113,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (143,1,'Indexation books',to_date('10-FEB-14','DD-MON-RR'),to_date('10-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (101,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (105,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (106,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (109,1,'Indexation books',to_date('06-FEB-14','DD-MON-RR'),to_date('06-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (126,1,'Indexation books',to_date('07-FEB-14','DD-MON-RR'),to_date('07-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (129,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (130,1,'Indexation books',to_date('08-FEB-14','DD-MON-RR'),to_date('08-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (148,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (149,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
Insert into EBOOKS.JOBS (JOB_ID,USER_ID,JOB_COMMENT,START_TIME,END_TIME) values (151,1,'Indexation books',to_date('11-FEB-14','DD-MON-RR'),to_date('11-FEB-14','DD-MON-RR'));
REM INSERTING into EBOOKS.LANGUAGES
SET DEFINE OFF;
Insert into EBOOKS.LANGUAGES (LANGUAGE_ID,LANGUAGE_NAME) values (37,'en');
Insert into EBOOKS.LANGUAGES (LANGUAGE_ID,LANGUAGE_NAME) values (39,'ru');
Insert into EBOOKS.LANGUAGES (LANGUAGE_ID,LANGUAGE_NAME) values (38,'uk');
REM INSERTING into EBOOKS.ROLES
SET DEFINE OFF;
Insert into EBOOKS.ROLES (ROLE_ID,ROLE_NAME) values (1,'ROLE_ADMIN');
Insert into EBOOKS.ROLES (ROLE_ID,ROLE_NAME) values (2,'ROLE_USER');
REM INSERTING into EBOOKS.SHARED
SET DEFINE OFF;
Insert into EBOOKS.SHARED (SHARED_ID,ID_BOOK,ID_OWNER,ID_GRANTEE) values (30,723,1,5);
REM INSERTING into EBOOKS.SHARED_TYPES
SET DEFINE OFF;
Insert into EBOOKS.SHARED_TYPES (SHARED_TYPE_ID,SHARED_TYPE_NAME) values (2,'PRIVATE');
Insert into EBOOKS.SHARED_TYPES (SHARED_TYPE_ID,SHARED_TYPE_NAME) values (1,'PUBLIC');
REM INSERTING into EBOOKS.USERS
SET DEFINE OFF;
Insert into EBOOKS.USERS (USER_ID,EMAIL,PASSWORD,FIRST_NAME,LAST_NAME,REGISTRATION_DATE,ROLE_ID) values (6,'test@example.com','$2a$10$f27UFtvXeiJ2YIlS7P2Qz.HZTHu2c6P9xGyWys1Gig7kIE614XzX6','John','Doe',to_date('10-FEB-14','DD-MON-RR'),2);
Insert into EBOOKS.USERS (USER_ID,EMAIL,PASSWORD,FIRST_NAME,LAST_NAME,REGISTRATION_DATE,ROLE_ID) values (5,'riaval@yandex.ru','$2a$10$J647LFoo4vgMY9wsFQWTAuAmy3GOg5sRdAYUWtl.3a/g0wR.xIrOO','Riaval','Test',to_date('07-FEB-14','DD-MON-RR'),2);
Insert into EBOOKS.USERS (USER_ID,EMAIL,PASSWORD,FIRST_NAME,LAST_NAME,REGISTRATION_DATE,ROLE_ID) values (1,'ls2294@gmail.com','$2a$10$OtRauvJLA34oOgDZLYL4/OyMxEdg0Yu9l8nkNNmCGpFoNXc1AnJk6','Dmitry','Zhukov',null,2);
--------------------------------------------------------
--  DDL for Index GENRES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."GENRES_PK" ON "EBOOKS"."GENRES" ("GENRE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ROLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."ROLES_PK" ON "EBOOKS"."ROLES" ("ROLE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USERS_UK3
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."USERS_UK3" ON "EBOOKS"."USERS" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index BOOKS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."BOOKS_PK" ON "EBOOKS"."BOOKS" ("BOOK_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SHARED_TYPES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."SHARED_TYPES_PK" ON "EBOOKS"."SHARED_TYPES" ("SHARED_TYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index JOBS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."JOBS_PK" ON "EBOOKS"."JOBS" ("JOB_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index LANGUAGES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."LANGUAGES_PK" ON "EBOOKS"."LANGUAGES" ("LANGUAGE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index GENRES_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."GENRES_UK1" ON "EBOOKS"."GENRES" ("GENRE_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."USERS_PK" ON "EBOOKS"."USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index BOOK_TYPES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."BOOK_TYPES_PK" ON "EBOOKS"."BOOK_TYPES" ("BOOK_TYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index BOOKS_GENRES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."BOOKS_GENRES_PK" ON "EBOOKS"."BOOKS_GENRES" ("BOOKS_GENRES_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index LANGUAGES_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."LANGUAGES_UK1" ON "EBOOKS"."LANGUAGES" ("LANGUAGE_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SHARED_TYPES_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."SHARED_TYPES_UK1" ON "EBOOKS"."SHARED_TYPES" ("SHARED_TYPE_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SHARED_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EBOOKS"."SHARED_PK" ON "EBOOKS"."SHARED" ("SHARED_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" MODIFY ("EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" MODIFY ("FIRST_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" MODIFY ("LAST_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."USERS" ADD CONSTRAINT "USERS_UK3" UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LANGUAGES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."LANGUAGES" ADD CONSTRAINT "LANGUAGES_PK" PRIMARY KEY ("LANGUAGE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."LANGUAGES" ADD CONSTRAINT "LANGUAGES_UK1" UNIQUE ("LANGUAGE_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."LANGUAGES" MODIFY ("LANGUAGE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."LANGUAGES" MODIFY ("LANGUAGE_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BOOKS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."BOOKS" ADD CONSTRAINT "BOOKS_PK" PRIMARY KEY ("BOOK_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("BOOK_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("PUB_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("FILE_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("BOOK_SIZE" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("PATH" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("SHA1" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("ID_OWNER" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("ID_LANG" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("ID_BOOK_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS" MODIFY ("ID_SHARED_TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table JOBS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."JOBS" ADD CONSTRAINT "JOBS_PK" PRIMARY KEY ("JOB_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."JOBS" MODIFY ("JOB_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."JOBS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."JOBS" MODIFY ("JOB_COMMENT" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."JOBS" MODIFY ("START_TIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SHARED
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."SHARED" ADD CONSTRAINT "SHARED_PK" PRIMARY KEY ("SHARED_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."SHARED" MODIFY ("SHARED_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."SHARED" MODIFY ("ID_BOOK" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."SHARED" MODIFY ("ID_OWNER" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."SHARED" MODIFY ("ID_GRANTEE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BOOK_TYPES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."BOOK_TYPES" ADD CONSTRAINT "BOOK_TYPES_PK" PRIMARY KEY ("BOOK_TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOK_TYPES" MODIFY ("BOOK_TYPE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOK_TYPES" MODIFY ("BOOK_TYPE_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SHARED_TYPES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."SHARED_TYPES" ADD CONSTRAINT "SHARED_TYPES_PK" PRIMARY KEY ("SHARED_TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."SHARED_TYPES" ADD CONSTRAINT "SHARED_TYPES_UK1" UNIQUE ("SHARED_TYPE_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."SHARED_TYPES" MODIFY ("SHARED_TYPE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BOOKS_GENRES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."BOOKS_GENRES" ADD CONSTRAINT "BOOKS_GENRES_PK" PRIMARY KEY ("BOOKS_GENRES_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS_GENRES" MODIFY ("BOOKS_GENRES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS_GENRES" MODIFY ("BOOK_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."BOOKS_GENRES" MODIFY ("GENRE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."ROLES" ADD CONSTRAINT "ROLES_PK" PRIMARY KEY ("ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."ROLES" MODIFY ("ROLE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."ROLES" MODIFY ("ROLE_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GENRES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."GENRES" ADD CONSTRAINT "GENRES_PK" PRIMARY KEY ("GENRE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."GENRES" ADD CONSTRAINT "GENRES_UK1" UNIQUE ("GENRE_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "EBOOKS"."GENRES" MODIFY ("GENRE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EBOOKS"."GENRES" MODIFY ("GENRE_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table BOOKS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."BOOKS" ADD CONSTRAINT "BOOKS_FK" FOREIGN KEY ("ID_OWNER")
	  REFERENCES "EBOOKS"."USERS" ("USER_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS" ADD CONSTRAINT "SHARED_TYPES_FK2" FOREIGN KEY ("ID_LANG")
	  REFERENCES "EBOOKS"."LANGUAGES" ("LANGUAGE_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS" ADD CONSTRAINT "SHARED_TYPES_FK3" FOREIGN KEY ("ID_BOOK_TYPE")
	  REFERENCES "EBOOKS"."BOOK_TYPES" ("BOOK_TYPE_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS" ADD CONSTRAINT "SHARED_TYPES_FK4" FOREIGN KEY ("ID_SHARED_TYPE")
	  REFERENCES "EBOOKS"."SHARED_TYPES" ("SHARED_TYPE_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOOKS_GENRES
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."BOOKS_GENRES" ADD CONSTRAINT "BOOKS_GENRES_FK" FOREIGN KEY ("BOOK_ID")
	  REFERENCES "EBOOKS"."BOOKS" ("BOOK_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."BOOKS_GENRES" ADD CONSTRAINT "BOOKS_GENRES_FK2" FOREIGN KEY ("GENRE_ID")
	  REFERENCES "EBOOKS"."GENRES" ("GENRE_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table JOBS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."JOBS" ADD CONSTRAINT "JOBS_FK" FOREIGN KEY ("USER_ID")
	  REFERENCES "EBOOKS"."USERS" ("USER_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SHARED
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."SHARED" ADD CONSTRAINT "SHARED_FK" FOREIGN KEY ("ID_BOOK")
	  REFERENCES "EBOOKS"."BOOKS" ("BOOK_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."SHARED" ADD CONSTRAINT "SHARED_FK2" FOREIGN KEY ("ID_OWNER")
	  REFERENCES "EBOOKS"."USERS" ("USER_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "EBOOKS"."SHARED" ADD CONSTRAINT "SHARED_FK3" FOREIGN KEY ("ID_GRANTEE")
	  REFERENCES "EBOOKS"."USERS" ("USER_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "EBOOKS"."USERS" ADD CONSTRAINT "USERS_FK" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "EBOOKS"."ROLES" ("ROLE_ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_BOOKS
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_BOOKS" 
  before insert on "BOOKS"               
  for each row  
begin   
    select "BOOKS_SEQ".nextval into :NEW.BOOK_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_BOOKS" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_BOOKS_GENRES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_BOOKS_GENRES" 
  before insert on "BOOKS_GENRES"               
  for each row  
begin   
    select "BOOKS_GENRES_SEQ".nextval into :NEW.BOOKS_GENRES_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_BOOKS_GENRES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_BOOK_TYPES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_BOOK_TYPES" 
  before insert on "BOOK_TYPES"               
  for each row  
begin   
    select "BOOK_TYPES_SEQ".nextval into :NEW.BOOK_TYPE_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_BOOK_TYPES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_GENRES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_GENRES" 
  before insert on "GENRES"               
  for each row  
begin   
    select "GENRES_SEQ".nextval into :NEW.GENRE_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_GENRES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_JOBS
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_JOBS" 
  before insert on "JOBS"               
  for each row  
begin   
    select "JOBS_SEQ".nextval into :NEW.JOB_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_JOBS" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_LANGUAGES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_LANGUAGES" 
  before insert on "LANGUAGES"               
  for each row  
begin   
    select "LANGUAGES_SEQ".nextval into :NEW.LANGUAGE_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_LANGUAGES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_ROLES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_ROLES" 
  before insert on "ROLES"               
  for each row  
begin   
    select "ROLES_SEQ".nextval into :NEW.ROLE_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_ROLES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_SHARED
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_SHARED" 
  before insert on "SHARED"               
  for each row  
begin   
    select "SHARED_SEQ".nextval into :NEW.SHARED_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_SHARED" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_SHARED_TYPES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_SHARED_TYPES" 
  before insert on "SHARED_TYPES"               
  for each row  
begin   
    select "SHARED_TYPES_SEQ".nextval into :NEW.SHARED_TYPE_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_SHARED_TYPES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_USERS
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "EBOOKS"."BI_USERS" 
  before insert on "USERS"               
  for each row  
begin   
    select "USERS_SEQ".nextval into :NEW.USER_ID from dual; 
end; 

/
ALTER TRIGGER "EBOOKS"."BI_USERS" ENABLE;
--------------------------------------------------------
--  DDL for Function GET_BOOK_TYPE_ID_BY_NAME
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "EBOOKS"."GET_BOOK_TYPE_ID_BY_NAME" 
(
  pBookTypeName IN VARCHAR2 
) RETURN NUMBER AS 
  vBookTypeId number(6);
BEGIN
  SELECT DISTINCT(BOOK_TYPE_ID) INTO vBookTypeId FROM BOOK_TYPES WHERE BOOK_TYPE_NAME = pBookTypeName;
  RETURN vBookTypeId;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    INSERT INTO BOOK_TYPES (BOOK_TYPE_NAME) VALUES (pBookTypeName)
      RETURNING BOOK_TYPE_ID INTO vBookTypeId;
  RETURN vBookTypeId;
END GET_BOOK_TYPE_ID_BY_NAME;

/
--------------------------------------------------------
--  DDL for Function GET_GENRE_ID_BY_NAME
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "EBOOKS"."GET_GENRE_ID_BY_NAME" 
(
  pGenreName IN VARCHAR2 
) RETURN VARCHAR2 AS 
  vGenreId number(6);
BEGIN
  SELECT DISTINCT(GENRE_ID) INTO vGenreId FROM GENRES WHERE GENRE_NAME = pGenreName;
  RETURN vGenreId;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    INSERT INTO GENRES (GENRE_NAME) VALUES (pGenreName)
      RETURNING GENRE_ID INTO vGenreId;
  RETURN vGenreId;
END GET_GENRE_ID_BY_NAME;

/
--------------------------------------------------------
--  DDL for Function GET_LANGUAGE_ID_BY_NAME
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "EBOOKS"."GET_LANGUAGE_ID_BY_NAME" 
(
  pLanguageName IN VARCHAR2 
) RETURN NUMBER AS 
  vLanguageId number(6);
BEGIN
  SELECT DISTINCT(LANGUAGE_ID) INTO vLanguageId FROM LANGUAGES WHERE LANGUAGE_NAME = pLanguageName;
  RETURN vLanguageId;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    INSERT INTO LANGUAGES (LANGUAGE_NAME) VALUES (pLanguageName)
      RETURNING LANGUAGE_ID INTO vLanguageId;
  RETURN vLanguageId;
END GET_LANGUAGE_ID_BY_NAME;

/
--------------------------------------------------------
--  DDL for Function GET_SHARED_TYPE_ID_BY_NAME
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "EBOOKS"."GET_SHARED_TYPE_ID_BY_NAME" 
(
  pSharedTypeName IN VARCHAR2 
) RETURN NUMBER AS 
  vSharedTypeName number(6);
BEGIN
  SELECT DISTINCT(SHARED_TYPE_ID) INTO vSharedTypeName FROM SHARED_TYPES WHERE SHARED_TYPE_NAME = pSharedTypeName;
  RETURN vSharedTypeName;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    INSERT INTO SHARED_TYPES (SHARED_TYPE_NAME) VALUES (pSharedTypeName)
      RETURNING SHARED_TYPE_ID INTO vSharedTypeName;
  RETURN vSharedTypeName;
END GET_SHARED_TYPE_ID_BY_NAME;

/
--------------------------------------------------------
--  DDL for Function INSERT_BOOK
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "EBOOKS"."INSERT_BOOK" 
(
  pAuthor IN VARCHAR2 
, pTitle IN VARCHAR2 
, pUploadedDate IN DATE 
, pFileName IN VARCHAR2 
, pFileSize IN NUMBER 
, pFilePath IN VARCHAR2 
, pSHA1 IN VARCHAR2 
, pOwner IN VARCHAR2 
, pLanguage IN VARCHAR2 
, pBookType IN VARCHAR2 
, pAnnotation IN VARCHAR2 
, pISBN IN VARCHAR2 
, pSharedType IN VARCHAR2 
)
RETURN NUMBER AS 
  vLanguageId number(6);
  vBookTypeId number(6);
  vSharedTypeId number(6);
  vBookId number(6);
BEGIN
    vLanguageId := GET_LANGUAGE_ID_BY_NAME(pLanguage);
    vBookTypeId := GET_BOOK_TYPE_ID_BY_NAME(pBookType);
    vSharedTypeId := GET_SHARED_TYPE_ID_BY_NAME(pSharedType);
    
    INSERT INTO
          BOOKS (
            AUTHOR,
            TITLE,
            PUB_DATE,
            FILE_NAME,
            BOOK_SIZE,
            PATH,
            SHA1,
            ID_OWNER,
            ID_LANG,
            ID_BOOK_TYPE,
            ANNOTATION,
            ISBN,
            ID_SHARED_TYPE
          ) VALUES (
            pAuthor,
            pTitle,
            pUploadedDate,
            pFileName,
            pFileSize,
            pFilePath,
            pSHA1,
            (SELECT USER_ID FROM USERS WHERE EMAIL = pOwner),
            vLanguageId,
            vBookTypeId,
            pAnnotation,
            pISBN,
            vSharedTypeId
          )
      RETURNING BOOK_ID INTO vBookId;
    
  RETURN vBookId;
END INSERT_BOOK;

/
