--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 9.6.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: city; Type: TABLE; Schema: public; Owner: dg
--

CREATE TABLE public.city (
    id integer NOT NULL,
    country_id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.city OWNER TO dg;

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: dg
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_id_seq OWNER TO dg;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dg
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- Name: country; Type: TABLE; Schema: public; Owner: dg
--

CREATE TABLE public.country (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.country OWNER TO dg;

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: dg
--

CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO dg;

--
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dg
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- Name: zip; Type: TABLE; Schema: public; Owner: dg
--

CREATE TABLE public.zip (
    id integer NOT NULL,
    country_id integer NOT NULL,
    code character varying(20),
    name character varying(50)
);


ALTER TABLE public.zip OWNER TO dg;

--
-- Name: zip_id_seq; Type: SEQUENCE; Schema: public; Owner: dg
--

CREATE SEQUENCE public.zip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zip_id_seq OWNER TO dg;

--
-- Name: zip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dg
--

ALTER SEQUENCE public.zip_id_seq OWNED BY public.zip.id;


--
-- Name: city id; Type: DEFAULT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- Name: country id; Type: DEFAULT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- Name: zip id; Type: DEFAULT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.zip ALTER COLUMN id SET DEFAULT nextval('public.zip_id_seq'::regclass);


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: dg
--

COPY public.city (id, country_id, name) FROM stdin;
1	1	Graz
2	1	Linz
3	1	Salzburg
4	1	Wien
5	2	Bologna
6	2	Firenze
7	2	Roma
8	2	Venezia
9	3	Atlanta
10	3	Los Angeles
11	3	New York
12	3	Washington
\.


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dg
--

SELECT pg_catalog.setval('public.city_id_seq', 12, true);


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: dg
--

COPY public.country (id, name) FROM stdin;
1	Austria
2	Italy
3	USA
\.


--
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dg
--

SELECT pg_catalog.setval('public.country_id_seq', 4, true);


--
-- Data for Name: zip; Type: TABLE DATA; Schema: public; Owner: dg
--

COPY public.zip (id, country_id, code, name) FROM stdin;
1	1	8054	Graz-Webling
2	2	30124	Venezia
3	3	20001-6000	Metropolitan Washington Airports Authority
9	1	9020	Klagenfurt
\.


--
-- Name: zip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dg
--

SELECT pg_catalog.setval('public.zip_id_seq', 10, true);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: zip zip_pkey; Type: CONSTRAINT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.zip
    ADD CONSTRAINT zip_pkey PRIMARY KEY (id);


--
-- Name: city_name_idx; Type: INDEX; Schema: public; Owner: dg
--

CREATE UNIQUE INDEX city_name_idx ON public.city USING btree (name);


--
-- Name: country_name_idx; Type: INDEX; Schema: public; Owner: dg
--

CREATE UNIQUE INDEX country_name_idx ON public.country USING btree (name);


--
-- Name: zip_code_idx; Type: INDEX; Schema: public; Owner: dg
--

CREATE UNIQUE INDEX zip_code_idx ON public.zip USING btree (code);


--
-- Name: city city_foreignkey_country_id; Type: FK CONSTRAINT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_foreignkey_country_id FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- Name: zip zip_foreignkey_country_id; Type: FK CONSTRAINT; Schema: public; Owner: dg
--

ALTER TABLE ONLY public.zip
    ADD CONSTRAINT zip_foreignkey_country_id FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- PostgreSQL database dump complete
--

