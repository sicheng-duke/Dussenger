### TASK 1: Login Interface
In login interface, there are one text box and one password box at the middle of the interface. Beneath them are two buttons, a register button and a login button.

**function 1**: After clicking the login button, the login interface will vanish and the main interface will be opened (since currently the interface and server/database are not linked, there won't be password checking step).  
**function 2**: After clicking the register button, the login interface will vanish and the register interface will be opened. 

### TASK 2: Register Interface
In register interface, there are one text box for username setting, one password box for password setting, and one password box for password confirmation. Beneath them are two buttons, a confirm button and a cancel button.

**function 1**: After clicking the cancel button, the the register interface will vanish and the login interface will be reopened with out checking the content of the three boxes.  
**function 2**: After clicking the confirm button, it will check the content of the three boxes and act differently under 3 different conditions:  
* **Condition1**: Either the text box for username setting or the password box for password setting is empty (or both of them), error message will pop out and the data from the password setting box will be erased.
* **Condition2**:If the content in the password box for password setting and the content in the password box for password confirmation are not the same, error message will pop out and the data form both boxes will be erased.
* **Condition3**:If it's not under condition1 or condition3, then the register interface will vanish and the login interface will be reopened.

### TASK 3: Main Interface 
When user enters main interface, the interface is divided into three parts.
On left upper half is a box for user information and friend search function.
On left lower half is a box for friend list (currently user interface and server/database are not linked, so assume there are 50 friends for test).
On right part is a chat box.

**function 1**: in search text field, when friend requested is in friend list, the chat box for that friend will be opened, when friend is not in friend list, an other interface which provide adding new friend will be opened.  
**function 2**: in friend list, when mouse is on a person, that name becomes blue. When mouse left that person, the name becomes dark.  
**function 3**: in friend list, when the name of a friend is double clicked, the chat box of that friend is opened. In the chat box, there are one window for previous chat and one window for typing.  

### TASK 4: Server  
For the server,our program use Postgresql as Database to store user information and relationship between users.

To run the server on other computer, you need to install [Postgeresql](http://www.postgresql.org/download/) on your computer , uncomment the `initialize` method in Server.java,set user, password, database the same as them in init.java and then you can use the program to set up the new Database on your computer.

The Server code provide `initDatabase` method to create tables in the Database.  
It also provide a test code to add some initial user and relationship to the Database for testing.  
For future use, Server code provide `getFriend` ,`setUserInfo`, `setRelation` methods to get or set information in Database.