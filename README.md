# RegularFalsi
Java program to find Roots of any mathematical equation using RegularFalsi  method. It is a numerical method to find roots.

## Getting Started

You can download this project and run it into eclipse.
Calculation and solve of equations are based on my previous project [**String Calculator**](https://github.com/bablushaw23/StringCalculator). so it also supports huge number of functions. as well. 

## Prerequisites

**Needs are**:

 A working eclipse application with UTF-8(unicode) editor settings is best.(Reasons written on Alternative section).
 You can tune your text editor settings by going to window(on toolbar of eclipse)> preferences > General(on left) > content Types > Texts(under content types on right side)> (select it by single click on Text) > (At bottom of form you will find Default encoding: )> ( write: UTF-8 inside textbox) > (click <Apply and close> button).
 
 **Alternative**:
 
   Java compiler- To compile source code. But it may not run because i used (√, π) instead of their unicode characters. so, directly     compiling into cmd, terminal, powershell may give error.
   I have also provided .class files to just run and check. For this run: **RegularFalsiTester.class 

## Open the project in eclipse

1. Unzip(if zipped) the downloaded project.
2. Under file(in toolbox) > Open Projects from File System... >(click on <Directory> button) > (Redirect to unzipped folder) > 
	( <Select folder> button ) > (click on Finish)
	
3. Run **RegularFalsiTester

	
## Some sample test cases..(on live run, header of tables will be arranged properly.)
**1st Example.**

Enter the equation(in terms of x):
5x^4-50x+43
Is your equation contain any trignometric function like sin(), cos() etc?(y/n):
n
Correct upto no. of digits after decimal pt:
6


	_________________________________________________________________________________________________________
	| sr.|   x0			|   x1			|   x2			|   fx2			|
	_________________________________________________________________________________________________________
	|  1.|    1.000000		|    2.000000		|    1.080000		|   -4.197555		|
	|  2.|    1.080000		|    2.000000		|    1.221989		|   -6.950364		|
	|  3.|    1.221989		|    2.000000		|    1.402536		|   -7.779244		|
	|  4.|    1.402536		|    2.000000		|    1.553541		|   -5.552384		|
	|  5.|    1.553541		|    2.000000		|    1.640361		|   -2.816454		|
	|  6.|    1.640361		|    2.000000		|    1.679596		|   -1.188396		|
	|  7.|    1.679596		|    2.000000		|    1.695338		|   -0.462627		|
	|  8.|    1.695338		|    2.000000		|    1.701345		|   -0.174443		|
	|  9.|    1.701345		|    2.000000		|    1.703593		|   -0.064986		|
	| 10.|    1.703593		|    2.000000		|    1.704428		|   -0.024100		|
	| 11.|    1.704428		|    2.000000		|    1.704737		|   -0.008923		|
	| 12.|    1.704737		|    2.000000		|    1.704852		|   -0.003301		|
	| 13.|    1.704852		|    2.000000		|    1.704894		|   -0.001221		|
	| 14.|    1.704894		|    2.000000		|    1.704910		|   -0.000452		|
	| 15.|    1.704910		|    2.000000		|    1.704916		|   -0.000167		|
	| 16.|    1.704916		|    2.000000		|    1.704918		|   -0.000062		|
	| 17.|    1.704918		|    2.000000		|    1.704919		|   -0.000023		|
	| 18.|    1.704919		|    2.000000		|    1.704919		|   -0.000008		|
	| 19.|    1.704919		|    2.000000		|    1.704919		|   -0.000003		|
	| 20.|    1.704919		|    2.000000		|    1.704919		|   -0.000001		|
	| 21.|    1.704919		|    2.000000		|    1.704919		|   -0.000000		|
	| 22.|    1.704919		|    2.000000		|    1.704919		|   -0.000000		|
	| 23.|    1.704919		|    2.000000		|    1.704919		|   -0.000000		|
	| 24.|    1.704919		|    2.000000		|    1.704919		|   -0.000000		|


Root between 1 & 2 is: 1.7049191455104726...(correct upto 6 digits).

**2nd Example:**

Enter the equation(in terms of x):
x^2-10x+21
Is your equation contain any trignometric function like sin(), cos() etc?(y/n):
n
Correct upto no. of digits after decimal pt:
3


	_________________________________________________________________________________________________________
	| sr.|   x0			|   x1			|   x2			|   fx2			|
	_________________________________________________________________________________________________________
	|  1.|    2.000000		|    3.000000		|    3.000000		|    0.000000		|
		Root is:0.0


Root between 2 & 3 is: 3.0...(correct upto 3 digits).


	_________________________________________________________________________________________________________
	| sr.|   x0			|   x1			|   x2			|   fx2			|
	_________________________________________________________________________________________________________
	|  1.|    3.000000		|    4.000000		|    3.000000		|    0.000000		|
		Root is:0.0


Root between 3 & 4 is: 3.0...(correct upto 3 digits).


	_________________________________________________________________________________________________________
	| sr.|   x0			|   x1			|   x2			|   fx2			|
	_________________________________________________________________________________________________________
	|  1.|    6.000000		|    7.000000		|    7.000000		|    0.000000		|
		Root is:0.0


Root between 6 & 7 is: 7.0...(correct upto 3 digits).


	_________________________________________________________________________________________________________
	| sr.|   x0			|   x1			|   x2			|   fx2			|
	_________________________________________________________________________________________________________
	|  1.|    7.000000		|    8.000000		|    7.000000		|    0.000000		|
		Root is:0.0


Root between 7 & 8 is: 7.0...(correct upto 3 digits).

