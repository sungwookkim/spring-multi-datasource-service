DROP TABLE IF EXISTS member;
CREATE TABLE public."member" (
    member_seq bigserial NOT NULL,
    id varchar(128) NOT NULL,
    "name" varchar(20) NOT NULL,
    age int4 NOT NULL,
    reg_dtm timestamp(0) NOT NULL DEFAULT now(),
    CONSTRAINT member_pk PRIMARY KEY (member_seq)
);

DROP TABLE IF EXISTS public.free_board;
CREATE TABLE public.free_board (
    seq bigserial NOT NULL,
    member_id varchar(20) NOT NULL,
    "text" text NOT NULL,
    registration_date timestamp NOT NULL DEFAULT now(),
    CONSTRAINT free_board_pk PRIMARY KEY (seq)
);

DROP TABLE IF EXISTS public.game_board;
CREATE TABLE public.game_board (
    seq bigserial NOT NULL,
    member_id varchar(20) NOT NULL,
    "text" text NOT NULL,
    registration_date timestamp NOT NULL DEFAULT now(),
    CONSTRAINT game_board_pk PRIMARY KEY (seq)
);

DROP TABLE IF EXISTS public.notice_board_control;
CREATE TABLE public.notice_board_control (
    seq bigserial NOT NULL,
    reference_seq int8 NOT NULL,
    notice_board_type varchar(10) NOT NULL,
    registration_date timestamp NOT NULL DEFAULT now(),
    CONSTRAINT notice_board_control_pk PRIMARY KEY (seq)
);