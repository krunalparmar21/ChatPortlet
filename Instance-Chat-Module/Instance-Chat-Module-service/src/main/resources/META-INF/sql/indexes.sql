create index IX_4DA14CF7 on DLF_ChatConfigure (groupId);
create index IX_1548B047 on DLF_ChatConfigure (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_53FE9C89 on DLF_ChatConfigure (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_21679A8E on DLF_ChatMessage (toUser);
create index IX_C3D450A8 on DLF_ChatMessage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DC08E12A on DLF_ChatMessage (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7FF4CB61 on DLF_ChatRequest (isAccepted);
create index IX_6608E9F8 on DLF_ChatRequest (uuid_[$COLUMN_LENGTH:75$]);

create index IX_29CBCA56 on DLF_NEW_ChatConfigure (groupId);
create index IX_CBDBCDC8 on DLF_NEW_ChatConfigure (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D40D264A on DLF_NEW_ChatConfigure (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_27A2318F on DLF_NEW_ChatMessage (toUser);
create index IX_43E2DA69 on DLF_NEW_ChatMessage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FA00E72B on DLF_NEW_ChatMessage (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2919F9E2 on DLF_NEW_ChatRequest (isAccepted);
create index IX_B08EEED7 on DLF_NEW_ChatRequest (uuid_[$COLUMN_LENGTH:75$]);