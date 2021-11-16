-- Table: public.scrum_user_role

-- DROP TABLE public.scrum_user_role;

CREATE TABLE IF NOT EXISTS public.scrum_user_role
(
    id integer PRIMARY KEY,
    role_name character varying COLLATE pg_catalog."default" NOT NULL,
    role_permission character varying COLLATE pg_catalog."default" NOT NULL,
    create_time timestamp without time zone DEFAULT (now())::timestamp(0) without time zone,
    modify_time timestamp without time zone DEFAULT (now())::timestamp(0) without time zone,
    text1 character varying COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE public.scrum_user_role
    OWNER to postgres;

ALTER TABLE public.scrum_user_role
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 );

COMMENT ON TABLE public.scrum_user_role
    IS '用户角色表';

COMMENT ON COLUMN public.scrum_user_role.id
    IS '自增主键';

COMMENT ON COLUMN public.scrum_user_role.role_name
    IS '角色名称';

COMMENT ON COLUMN public.scrum_user_role.role_permission
    IS '角色权限';

COMMENT ON COLUMN public.scrum_user_role.create_time
    IS '创建日期';

COMMENT ON COLUMN public.scrum_user_role.modify_time
    IS '修改时间';

COMMENT ON COLUMN public.scrum_user_role.text1
    IS '备用字段';
-- Index: uni_role_name_idx

-- DROP INDEX public.uni_role_name_idx;

CREATE UNIQUE INDEX uni_role_name_idx
    ON public.scrum_user_role USING btree
    (role_name COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;