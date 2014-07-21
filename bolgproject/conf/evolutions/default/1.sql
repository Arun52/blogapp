# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table blog (
  id                        bigint not null,
  text                      varchar(255),
  created_at                timestamp,
  header                    varchar(255),
  constraint pk_blog primary key (id))
;

create table comment (
  id                        bigint not null,
  blog_id                   bigint not null,
  text                      varchar(255),
  constraint pk_comment primary key (id))
;

create table user (
  name                      varchar(255),
  password                  varchar(255),
  email                     varchar(255))
;

create sequence blog_seq;

create sequence comment_seq;

alter table comment add constraint fk_comment_blog_1 foreign key (blog_id) references blog (id) on delete restrict on update restrict;
create index ix_comment_blog_1 on comment (blog_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists blog;

drop table if exists comment;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists blog_seq;

drop sequence if exists comment_seq;

