DROP TABLE IF EXISTS member;

CREATE TABLE public."member" (
    member_seq bigserial NOT NULL,
    id varchar(128) NOT NULL,
    "name" varchar(20) NOT NULL,
    age int4 NOT NULL,
    reg_dtm timestamp(0) NOT NULL DEFAULT now(),
    CONSTRAINT member_pk PRIMARY KEY (member_seq)
);