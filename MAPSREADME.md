# Maps
## Custom Maps
### Colors
First, you must define the colors upu will use. 0 is
reserved for the walls and path and will never be
toggelable. For defining colors, you have two options.
Putting <br>
*C reg* <br>
will give you the same colors as in the default map.<br><br>
Alternatively, you could define custom colors like this<br>
*def Colors<br>
0O = (r,g,b)<br>
0C = (r,g,b)<br>
1 = (r,g,b)<br>
2 = (r,g,b)* <br>
0 must have a definition for both open (0O) and closed (0C),
while all the rest only get one number. You don't have to
use numbers for color definitions. All colors must be given a 
1 character identifier. Yes, emoji's work if you feel so inclined.
### Board
First, write def board to indicate board definition is starting.
Then, 10 by 10 grid of indentifiers.
Each Identifier starts with the color identifier, then has
a O or C for open and closed respectivly. Each row is on
a separate line, and each column is seperated by a space
*<br>def board<br>1O 0C 0O 0O 0O 0C 0C 0C 0C 0O<br>
0O 2C 0O 0C 0O 0C 0C 0O 3O 0O<br>
4O 0C 0O 0C 0O 0O 1C 0O 0C 0O<br>
0O 0C 0O 0C 0O 0C 0C 0C 0C 0O<br>
0O 5C 0O 0C 4C 0C 0O 3C 0O 0O<br>
0C 0C 0C 0C 0O 0C 0O 0C 0C 0O<br>
0C 0O 0O 0O 0O 0O 0O 0C 0C 0O<br>
0C 0O 0C 0C 0C 2O 0C 0C 0C 0O<br>
0C 0O 0C 0C 0C 0O 0C 0C 0C 5O<br>
0C 0O 0O 0O 0O 0O 0O 0O 6C 0O*

### Misc
#### Comments
Comments allow you to write into the file without
affecting it. The comments will be removed before the file
is processed. They can help you keep your bearings and 
remember why you did things. Creates comments with two
forward slashes.<br>
*//This is an example of a comment*
#### File
The map file is effectively just a text file. You can
easily make them in any word processor like ms Notepad,
etc. The file, while effectivly a text file, should be
stored as a .lizzy file.<br>
*level.lizzy*
## Example
C reg<br>
//default colors<br>
def board<br>
1O 0C 0O 0O 0O 0C 0C 0C 0C 0O<br>
0O 2C 0O 0C 0O 0C 0C 0O 3O 0O<br>
4O 0C 0O 0C 0O 0O 1C 0O 0C 0O<br>
0O 0C 0O 0C 0O 0C 0C 0C 0C 0O<br>
0O 5C 0O 0C 4C 0C 0O 3C 0O 0O<br>
0C 0C 0C 0C 0O 0C 0O 0C 0C 0O<br>
0C 0O 0O 0O 0O 0O 0O 0C 0C 0O<br>
0C 0O 0C 0C 0C 2O 0C 0C 0C 0O<br>
0C 0O 0C 0C 0C 0O 0C 0C 0C 5O<br>
0C 0O 0O 0O 0O 0O 0O 0O 6C 0O<br>