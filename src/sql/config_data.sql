USE configdb;
insert into roles(ROLE_ID,ROLE_NAME) values (1,'all');
insert into roles(ROLE_ID,ROLE_NAME) values (2,'guest');
insert into roles(ROLE_ID,ROLE_NAME) values (3,'root');
insert into roles(ROLE_ID,ROLE_NAME) values (4,'tenantadmin');
insert into roles(ROLE_ID,ROLE_NAME) values (5,'tenantuser'); 

insert into user_login ( tenantid, active, loginid, password, profile) values ( 1, 'Y', 'root@bizosys.com', '83FA7A7883F9FDE5D46139C439F1E1C1', 'role|root,user_display_name|root');

insert into objects(OBJECT_ID,OBJECT_NAME,OBJECT_TYPE) values 
(1,'login', 'user'),
(2,'tenantlogin', 'user'),
(3,'logout', 'user'),
(4,'getloggedinuser', 'user'),
(5,'register', 'user'),
(6,'changepassword', 'user'),
(7,'resetpassword', 'user'),
(8,'activate', 'user'),
(9,'select', 'sql'),
(10,'insert', 'sql'),
(11,'update', 'sql'),
(12,'refresh', 'sql'),
(13,'execute', 'sql'),
(14,'generateid', 'sql'),
(15,'upload', 'upload'),
(16,'GET_ROLES', 'sql'),
(17, 'GET_APP_CONFIG', 'sql'),
(18, 'GET_APP_CONFIG_COUNT', 'sql'),
(19, 'DELETE_FROM_APP_CONFIG_BY_ID', 'sql'),
(20, 'GET_AUTHORIZED_USERS_FOR_AN_ACTION', 'sql'),
(21, 'ADD_AN_APP_CONFIG', 'sql'),
(22, 'ADD_A_NEW_OBJECT', 'sql'),
(23, 'UPDATE_APP_CONFIG', 'sql'),
(24, 'REVOKE_PERMISSION_FOR_A_USER', 'sql'),
(25, 'GET_MAX_OBJECT_ID', 'sql'),
(26, 'CHANGE_PERMISSION_FOR_A_USER', 'sql'),
(27, 'GET_EXISTING_OBJECT_TYPES', 'sql'),
(28, 'GRANT_NEW_USER_PERMISSION', 'sql'),
(29, 'CHECK_OBJECT_EXISTENCE', 'sql'),
(30, 'DELETE_OBJECT_BY_ID', 'sql'),
(31, 'REMOVE_PERMISSION_BY_OBJECT_ID', 'sql'),
(32, 'EMPTY_APP_CONFIG', 'sql'),
(33, 'GET_APP_CONFIG_BY_ID', 'sql'),
(34, 'UPDATE_OBJECT_BY_NAME', 'sql'),
(35, 'GET_FUNCTION', 'sql'),
(36, 'GET_FUNCTION_COUNT', 'sql'),
(37, 'version', 'sql'),
(38, 'refreshFuncAuth', 'sql'),
(39, 'GET_FUNCTION_BY_ID', 'sql'),
(40, 'createsp', 'sql'),
(41, 'deletesp', 'sql'),
(42, 'GET_SP_APP_CONFIG', 'sql'),
(43, 'GET_SP_APP_CONFIG_COUNT', 'sql'),
(44, 'DELETE_FROM_SP_APP_CONFIG_BY_ID', 'sql'),
(45, 'GET_APP_CONFIG_BY_ID', 'sql'),
(46, 'UPDATE_SP_APP_CONFIG', 'sql'),
(47, 'GET_SP_BY_TITLE', 'sql');

insert into roleprivileges(TENANT_ID, ROLE_ID,OBJECT_ID,PERMISSION_LEVEL) values 
(NULL, 1, 1,  '--x'),
(NULL, 1, 2,  '--x'),
(NULL, 1, 3,  '--x'),
(NULL, 1, 4,  '--x'),
(NULL, 3, 5,  '--x'),
(NULL, 1, 6,  '--x'),
(NULL, 1, 7,  '--x'),
(NULL, 3, 8,  '--x'),
(NULL, 1, 9,  '--x'),
(NULL, 1, 10,  '--x'),
(NULL, 1, 11,  '--x'),
(NULL, 3, 12,  '--x'),
(NULL, 1, 13,  '--x'),
(NULL, 1, 14,  '--x'),
(NULL, 1, 15,  '--x'),
(NULL, 3, 16,  '--x'),
(NULL, 3, 17,  '--x'),
(NULL, 3, 18,  '--x'),
(NULL, 3, 19,  '--x'),
(NULL, 3, 20,  '--x'),
(NULL, 3, 21,  '--x'),
(NULL, 3, 22,  '--x'),
(NULL, 3, 23,  '--x'),
(NULL, 3, 24,  '--x'),
(NULL, 3, 25,  '--x'),
(NULL, 3, 26,  '--x'),
(NULL, 3, 27,  '--x'),
(NULL, 3, 28,  '--x'),
(NULL, 3, 29,  '--x'),
(NULL, 3, 30,  '--x'),
(NULL, 3, 31,  '--x'),
(NULL, 3, 32,  '--x'),
(NULL, 3, 33,  '--x'),
(NULL, 3, 34,  '--x'),
(NULL, 3, 35,  '--x'),
(NULL, 3, 36,  '--x'),
(NULL, 1, 37,  '--x'),
(NULL, 1, 38,  '--x'),
(NULL, 3, 39,  '--x'),
(NULL, 3, 40,  '--x'),
(NULL, 3, 41,  '--x'),
(NULL, 3, 42,  '--x'),
(NULL, 3, 43,  '--x'),
(NULL, 3, 44,  '--x'),
(NULL, 3, 45,  '--x'),
(NULL, 3, 46,  '--x'),
(NULL, 3, 47,  '--x');



insert into app_config(system, configtype,title,body, variables) values
('Y', 'I', 'ADD_SEQUENCE', 'insert into idsequence values(?,?)','' ),
('Y', 'S', 'GET_APP_CONFIG', 'SELECT configtype, title, body, variables, outvar, status FROM app_config WHERE 1 = 1 AND ( __where ) ORDER BY __sort LIMIT __limit OFFSET __offset','' ),
('Y', 'S', 'GET_APP_CONFIG_COUNT', 'SELECT COUNT(status) AS total FROM app_config WHERE 1 = 1 AND ( __where )','' ),
('Y', 'D', 'DELETE_FROM_APP_CONFIG_BY_ID', 'DELETE FROM app_config WHERE title = ?','' ),
('Y', 'I', 'ADD_AN_APP_CONFIG', 'INSERT INTO app_config(configtype, title, body, variables, outvar, status) VALUES(?,?,?,?,?,?)','' ),
('Y', 'U', 'UPDATE_APP_CONFIG', 'UPDATE app_config SET configtype = ?, title = ?, body = ?, variables = ?, outvar = ?, status = ? WHERE title = ?',''),
('Y', 'S', 'GET_APP_CONFIG_BY_ID', 'SELECT title, status, body, variables, outvar, configtype FROM app_config WHERE title = ?','' ),
('Y', 'S', 'GET_ROLES','SELECT ROLE_ID AS roleId, ROLE_NAME AS roleName FROM roles',''),
('Y', 'S', 'GET_AUTHORIZED_USERS_FOR_AN_ACTION', 'SELECT obj.OBJECT_ID as objectid, sauth.rolename, sauth.objecttype, sauth.objectname FROM objects obj, sauthorization sauth WHERE sauth.objectname = obj.OBJECT_NAME AND obj.OBJECT_NAME = ?','' ),
('Y', 'D', 'REVOKE_PERMISSION_FOR_A_USER', 'DELETE FROM roleprivileges WHERE ROLE_ID = (SELECT ROLE_ID FROM roles WHERE ROLE_NAME = ?) AND OBJECT_ID = ?','' ),
('Y', 'S', 'GET_MAX_OBJECT_ID', 'SELECT MAX(OBJECT_ID) as maxObjectId FROM objects','' ),
('Y', 'I', 'ADD_A_NEW_OBJECT', 'INSERT INTO objects(OBJECT_NAME, OBJECT_TYPE) VALUES(?,''sql'')','' ),
('Y', 'U', 'CHANGE_PERMISSION_FOR_A_USER', 'UPDATE roleprivileges SET ROLE_ID = ? WHERE ROLE_ID = ? AND OBJECT_ID = ?','' ),
('Y', 'S', 'GET_EXISTING_OBJECT_TYPES', 'SELECT DISTINCT OBJECT_TYPE as objectTypes FROM objects','' ),
('Y', 'I', 'GRANT_NEW_USER_PERMISSION', 'INSERT INTO roleprivileges(ROLE_ID, OBJECT_ID, PERMISSION_LEVEL) VALUES(?,?,''--x'')','' ),
('Y', 'S', 'CHECK_OBJECT_EXISTENCE', 'SELECT COUNT(OBJECT_NAME) AS count, OBJECT_ID, OBJECT_TYPE FROM objects WHERE OBJECT_NAME = ?','' ),
('Y', 'D', 'DELETE_OBJECT_BY_ID', 'DELETE FROM objects WHERE OBJECT_ID = ?',''),
('Y', 'D', 'REMOVE_PERMISSION_BY_OBJECT_ID', 'DELETE FROM roleprivileges WHERE OBJECT_ID = ?','' ),
('Y', 'U', 'UPDATE_OBJECT_BY_NAME', 'UPDATE objects SET OBJECT_NAME = ? WHERE OBJECT_NAME = ?','' ),
('Y', 'I', 'ADD_FUNCTION', 'Insert into sqlfunctions values(?,?,?)','' ),
('Y', 'D', 'DELETE_FUNCTION', 'Delete from sqlfunctions where funcId=?','' ),
('Y', 'U', 'UPDATE_FUNCTION', 'update sqlfunctions set status=?,funcId=? ,funcBody=? where funcId=?','' ),
('Y', 'S', 'DISPLAY_ALL_FUNCTION_NAMES', 'select funcId from sqlfunctions','' ),
('Y', 'S', 'DISPLAY_FUNCTION_BODY', 'select funcBody from sqlfunctions where funcId=?','' ),
('Y', 'S', 'GET_FUNCTION', 'SELECT status, funcId, funcBody FROM sqlfunctions WHERE 1 = 1 AND ( __where ) ORDER BY __sort LIMIT __limit OFFSET __offset','' ),
('Y', 'S', 'GET_FUNCTION_COUNT', 'SELECT COUNT(status) AS total FROM sqlfunctions WHERE 1 = 1 AND ( __where )','' ),
('Y', 'S', 'GET_FUNCTION_BY_ID', 'SELECT status, funcId, funcBody FROM sqlfunctions WHERE funcId = ?','' ),
('Y', 'S', 'GET_SP_APP_CONFIG','SELECT sp_title, sp_body, sp_poolname, sp_call_syntax, sp_out_var, sp_err_var, status FROM stored_proc_config WHERE 1 = 1 AND ( __where ) ORDER BY __sort LIMIT __limit OFFSET __offset',''),
('Y', 'S', 'GET_SP_APP_CONFIG_COUNT','SELECT COUNT(status) AS total FROM stored_proc_config WHERE 1 = 1 AND ( __where )',''),
('Y', 'D', 'DELETE_FROM_SP_APP_CONFIG_BY_ID','DELETE FROM stored_proc_config WHERE sp_title = ?',''),
('Y', 'S', 'GET_SP_APP_CONFIG_BY_ID','SELECT sp_title, sp_body, sp_poolname, sp_call_syntax, sp_out_var, sp_err_var, status FROM stored_proc_config WHERE sp_title = ?',''),
('Y', 'U', 'UPDATE_SP_APP_CONFIG','UPDATE stored_proc_config SET sp_title = ?, sp_body = ?, sp_call_syntax = ?, sp_out_var = ?, sp_err_var = ?, status = ? WHERE sp_title = ?',''),
('Y', 'I', 'ADD_SP_APP_CONFIG','INSERT INTO stored_proc_config(sp_title, sp_body, sp_poolname, sp_call_syntax, sp_out_var, sp_err_var, status) VALUES(?,?,?,?,?,?,?)',''),
('Y', 'S', 'GET_SP_BY_TITLE', 'SELECT id, sp_title, sp_body, sp_poolname, sp_call_syntax, sp_out_var, sp_err_var, status FROM stored_proc_config WHERE sp_title = ?','' ),
('Y', 'I', 'ADD_DB_CONFIG','INSERT INTO db_config(machineIp,dbPort,poolName,driverClass,connectionUrl,dbUser,dbUserPassword) VALUES(?,?,?,?,?,?,?)',''),
('Y', 'D', 'REMOVE_FROM_DB_CONFIG','delete from db_config where machineIp = ? AND poolName = ?','');