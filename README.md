##TASK 3: Main Interface
When user enters main interface, the interface is divided into three parts.
On left upper half is a box for user information and friend search function.
On left lower half is a box for friend list (currently user interface and server/database is not linked, so assume there are 50 friends for test).
On right part is a chat box.

function 1: in search text field, when friend requested is in friend list, the chat box for that friend will be opened. when friend is not in friend list, an other interface which provide adding new friend.

function 2: in friend list, when mouse is on a person, that name becomes blue. When mouse left that person, the name becomes dark.
function 3: in friend list, when the name of a friend is double clicked, the chat box of that friend is opened. In the chat box, there are one window for privous chat and one window for typing.

##TASK 4: Server
Server Code:
For the server,our program use Postgresql as Database to store user information and relationship between users.

To run the server on other computer, you need to install Postgeresql on your computer and then you can use the program to set up the new Database on your computer.

The Server code provide initial method to create tables in the Database.
It also provide a test code to add some initial user and relationship to the Databse for testing.
For futuer use, Server code provide GetFriend ,setUserInfo, setRelation methods to get or set information in Database.

