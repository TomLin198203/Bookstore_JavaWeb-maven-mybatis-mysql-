# Bookstore_JavaWeb-maven-mybatis-mysql-
簡介:
	這是簡單的網路書店網站
使用技術
	maven + mybatis框架(使用註解配置) + mysql

功能
1.前台
	用戶登入
	用戶註冊(含用戶激活(發mail給用戶))
	購物車(清空所有條目,刪除指定條目,查詢我的購物車)
	訂單(生成訂單,我的訂單,確認收貨)
	圖書查詢(查詢所有圖書,按分類查詢圖書)
2.後台
	管理員登入
	圖書分類管理(添加分類,查看所有分類,刪除分類,修改分類)
	圖書管理(查看所有圖書,刪除圖書,修改圖書,添加圖書(上傳圖片)(校驗圖片尺寸))
		圖書查詢 有分頁功能
	訂單(查詢所有訂單,按狀態查詢訂單,發貨,取消訂單)


jdbcConfig.properties (src/main/resources/)
	這為設置數據庫的名稱, 數據庫用戶登入的帳號及密碼

email_template.properties (src/main/resources/)
	這為發送mail的設置, 此設置是從gmail server發送mail. 
	uname: 為寄送mail的username, 
	pwd:為寄送mail的password,
	from:寄件人的mail

步驟:
1. 下載Mysql, 創建數據庫名稱為 bookstore ,並執行sql中createSQL.txt和 SQLData.txt 的內容
2. 下載Tomcat
3. 使用 Intellij Idea 導入project
4. 運行


用戶帳號:User   密碼:12345
管理員帳號:Admin   密碼:12345
用戶登入網址: http://localhost/bookstore 進入此按登入會跳到用戶登入頁面
管理員登入頁面: http://localhost/bookstore/adminjsps/login.jsp
