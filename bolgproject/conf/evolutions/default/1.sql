# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table blog (
  id                        bigint not null,
  text                      varchar(255),
  created_at                timestamp,
  header                    varchar(255),
  user_email                varchar(255),
  constraint pk_blog primary key (id))
;

create table comment (
  id                        bigint not null,
  text                      varchar(255),
  user_email                varchar(255),
  blog_id                   bigint,
  constraint pk_comment primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence blog_seq;

create sequence comment_seq;

create sequence user_seq;

alter table blog add constraint fk_blog_user_1 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_blog_user_1 on blog (user_email);
alter table comment add constraint fk_comment_user_2 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_comment_user_2 on comment (user_email);
alter table comment add constraint fk_comment_blog_3 foreign key (blog_id) references blog (id) on delete restrict on update restrict;
create index ix_comment_blog_3 on comment (blog_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists blog;

drop table if exists comment;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists blog_seq;

drop sequence if exists comment_seq;

drop sequence if exists user_seq;

