--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Ubuntu 12.2-2.pgdg19.10+1)
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-2.pgdg19.10+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: vmazuryk_demo1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE vmazuryk_demo1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE vmazuryk_demo1 OWNER TO postgres;

\connect vmazuryk_demo1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: advertisements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advertisements (
    id integer NOT NULL,
    title character varying(100),
    description text,
    created_date timestamp without time zone DEFAULT now(),
    expiration_date date,
    status integer,
    category_id integer,
    user_id integer,
    short_description text
);


ALTER TABLE public.advertisements OWNER TO postgres;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    title character varying(50),
    parent integer DEFAULT 0
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: categories_cat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categories_cat_id_seq OWNER TO postgres;

--
-- Name: categories_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_cat_id_seq OWNED BY public.categories.id;


--
-- Name: favorites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorites (
    id integer NOT NULL,
    advertisement_id integer,
    user_id integer
);


ALTER TABLE public.favorites OWNER TO postgres;

--
-- Name: favorits_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.favorits_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.favorits_id_seq OWNER TO postgres;

--
-- Name: favorits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.favorits_id_seq OWNED BY public.favorites.id;


--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    mess_id integer NOT NULL,
    mess_author_id integer,
    mess_post_id integer,
    mes_text character varying(100),
    mess_created_date timestamp without time zone
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_mess_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_mess_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_mess_id_seq OWNER TO postgres;

--
-- Name: messages_mess_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_mess_id_seq OWNED BY public.messages.mess_id;


--
-- Name: posts_post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.posts_post_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.posts_post_id_seq OWNER TO postgres;

--
-- Name: posts_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.posts_post_id_seq OWNED BY public.advertisements.id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    description character varying(15)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_role_id_seq OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_role_id_seq OWNED BY public.roles.id;


--
-- Name: statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.statuses (
    id integer NOT NULL,
    description character varying(15)
);


ALTER TABLE public.statuses OWNER TO postgres;

--
-- Name: statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.statuses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.statuses_id_seq OWNER TO postgres;

--
-- Name: statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.statuses_id_seq OWNED BY public.statuses.id;


--
-- Name: tokens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tokens (
    id integer NOT NULL,
    user_id integer,
    token text
);


ALTER TABLE public.tokens OWNER TO postgres;

--
-- Name: tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tokens_id_seq OWNER TO postgres;

--
-- Name: tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tokens_id_seq OWNED BY public.tokens.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(25),
    name character varying(25),
    phone character varying(25),
    password text,
    role integer,
    status integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: advertisements id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisements ALTER COLUMN id SET DEFAULT nextval('public.posts_post_id_seq'::regclass);


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_cat_id_seq'::regclass);


--
-- Name: favorites id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites ALTER COLUMN id SET DEFAULT nextval('public.favorits_id_seq'::regclass);


--
-- Name: messages mess_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN mess_id SET DEFAULT nextval('public.messages_mess_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_role_id_seq'::regclass);


--
-- Name: statuses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.statuses ALTER COLUMN id SET DEFAULT nextval('public.statuses_id_seq'::regclass);


--
-- Name: tokens id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens ALTER COLUMN id SET DEFAULT nextval('public.tokens_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: advertisements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.advertisements (id, title, description, created_date, expiration_date, status, category_id, user_id, short_description) FROM stdin;
40	Honda Civic 2017 Sports Edition	<h3>Honda Civic 2017 Sports Edition is the best car</h3>	2020-03-03 12:21:00.284597	2020-04-04	1	2	32	Honda Civic 2017 Sports Edition
41	Honda Civic 2018 Sports Edition	<h3>Honda Civic 2017 Sports Edition is the best car</h3>	2020-03-03 12:23:26.622059	2020-04-04	1	7	32	Honda Civic 2018 Sports Edition
42	Honda Civic 2016 Sports Edition	<h3>Honda Civic 2017 Sports Edition is the best car</h3>	2020-03-03 12:40:18.949005	2022-07-03	1	9	32	Honda Civic 2016 Sports Edition
43	Honda Civic 2015 Sports Edition	<h3>Honda Civic 2017 Sports Edition is the best car</h3>	2020-03-03 12:43:22.119566	2021-06-03	1	9	32	Honda Civic 2015 Sports Edition
46	Silicone Original Case For iPhone 11 Pro Max 7 8 6 6S Plus XR XS Max	<p><strong>Product Description</strong></p><p>Compatible iPhone Model: iPhone XR,iPhone 6 Plus,iPhone 6,iPhone XS,iPhone 6s,iPhone XS MAX,iPhone 7,iPhone 7 Plus,iPhone X,iPhone 8,iPhone 6s plus,iPhone 8 Plus<br />Design: Vintage,Quotes &amp; Messages,Marble,Cute,Glossy,Matte,Plain,Patterned,Exotic,Geometric,Sports,Abstract<br />Type: Fitted Case<br />Retail Package: Yes<br />Function: Dirt-resistant<br />Brand Name: KISSCASE<br />Size: Phone Case For iPhone 6 6S 6S Plus 7 8 7 Plus 8 Plus X XS XR MAX<br />Features: Fashion Macaron Silicone Phone Case For iPhone 8 7 X<br />Compatible Brand: Apple iPhones<br />Feature: Fashion Macaron Silicone Phone Case<br />Model: For Apple iPhone 5 6 6S 6S Plus 7 8 7 Plus 8 Plus X XS XR MAX</p>	2020-03-03 16:21:52.061763	2020-04-04	1	9	32	Compatible iPhone Model: iPhone XR,iPhone 6 Plus,iPhone 6,iPhone XS,iPhone 6s,iPhone XS MAX,iPhone 7,iPhone 7 Plus,iPhone X,iPhone 8,iPhone 6s plus,iPhone 8 Plus
45	Silicone Original Case For iPhone 11 Pro Max 7 8 6 6S Plus XR XS Max	<p><strong>Product Description</strong></p><p>Compatible iPhone Model: iPhone XR,iPhone 6 Plus,iPhone 6,iPhone XS,iPhone 6s,iPhone XS MAX,iPhone 7,iPhone 7 Plus,iPhone X,iPhone 8,iPhone 6s plus,iPhone 8 Plus<br />Design: Vintage,Quotes &amp; Messages,Marble,Cute,Glossy,Matte,Plain,Patterned,Exotic,Geometric,Sports,Abstract<br />Type: Fitted Case<br />Retail Package: Yes<br />Function: Dirt-resistant<br />Brand Name: KISSCASE<br />Size: Phone Case For iPhone 6 6S 6S Plus 7 8 7 Plus 8 Plus X XS XR MAX<br />Features: Fashion Macaron Silicone Phone Case For iPhone 8 7 X<br />Compatible Brand: Apple iPhones<br />Feature: Fashion Macaron Silicone Phone Case<br />Model: For Apple iPhone 5 6 6S 6S Plus 7 8 7 Plus 8 Plus X XS XR MAX</p><p>&nbsp;</p>	2020-03-03 16:21:22.172351	2020-04-04	1	9	32	Compatible iPhone Model: iPhone XR,iPhone 6 Plus,iPhone 6,iPhone XS,iPhone 6s,iPhone XS MAX,iPhone 7,iPhone 7 Plus,iPhone X,iPhone 8,iPhone 6s plus,iPhone 8 Plus
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, title, parent) FROM stdin;
2	Cars	0
7	Flowers	0
4	house	0
9	test category	0
\.


--
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favorites (id, advertisement_id, user_id) FROM stdin;
1	24	25
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (mess_id, mess_author_id, mess_post_id, mes_text, mess_created_date) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, description) FROM stdin;
1	ADMIN
2	MANAGER
3	USER
\.


--
-- Data for Name: statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.statuses (id, description) FROM stdin;
1	NEW
2	ACTIVE
3	BLOCKED
\.


--
-- Data for Name: tokens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tokens (id, user_id, token) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, name, phone, password, role, status) FROM stdin;
11	admin@admin.com	admin	5484	914bd955b938f2303414710fa8dfa53598d2ed6b	0	1
12	manager@manager.com	test manager	444444	914bd955b938f2303414710fa8dfa53598d2ed6b	1	1
32	vasja_maz@ukr.net	Test user	5211851515	914bd955b938f2303414710fa8dfa53598d2ed6b	2	1
\.


--
-- Name: categories_cat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_cat_id_seq', 10, true);


--
-- Name: favorits_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favorits_id_seq', 3, true);


--
-- Name: messages_mess_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_mess_id_seq', 1, false);


--
-- Name: posts_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.posts_post_id_seq', 46, true);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_role_id_seq', 1, true);


--
-- Name: statuses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.statuses_id_seq', 3, true);


--
-- Name: tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tokens_id_seq', 35, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 32, true);


--
-- Name: categories categories_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pk PRIMARY KEY (id);


--
-- Name: favorites favorits_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorits_pk PRIMARY KEY (id);


--
-- Name: messages messages_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pk PRIMARY KEY (mess_id);


--
-- Name: advertisements posts_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisements
    ADD CONSTRAINT posts_pk PRIMARY KEY (id);


--
-- Name: roles roles_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pk PRIMARY KEY (id);


--
-- Name: statuses statuses_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.statuses
    ADD CONSTRAINT statuses_pk PRIMARY KEY (id);


--
-- Name: tokens tokens_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens
    ADD CONSTRAINT tokens_pk PRIMARY KEY (id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

