SYSTEM CONFIGURATIONS:

OS: Windows 8.1 or above 64bit
Language: PHP, Javascript and Mysql
XAMPP: version 7.4.3


COMPILATION STEPS:

1.Download Xampp version 7.4.3 and install in C:Drive

2.Copy entire project source code folder, project1, from path src and paste to C:\xampp\htdocs folder

3.Start Xampp and then start Apache and MySQL modules on ports 80 and 3306 respectively.

4.On successful start of both Load url: http://localhost/project1/index.php on the browser

5.Load url: http://localhost/phpmyadmin/

6.Click on new->select import on the right handside panel->choose file contactbook.sql and click go to load the schema.
This .sql files are present on path sql files.
These .sql files have to be imported in the order followed in these steps to endure foreign key constraints.

7.Click on new->select import on the right handside panel->choose file-> contactbookinsertions1.sql and click go to insertions for tables contact, address and phone.

8.Click on new->select import on the right handside panel->choose file-> contactbookinsertions2.sql and click go to insertions for table dates.

Database and code is now ready to run and test.


