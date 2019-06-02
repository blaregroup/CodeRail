#
#	Author:
#			Suraj Singh Bisht
#			surajsinghbisht054@gmail.com
#
# ======================================================================
#	                    README
# ======================================================================
# This Script is Written, To Provide One Command To Start 
# CodeRail Project Java Codes.
#
#	If You are first time user, Then you have to make this script executable
#
#		command
#          :-$ chmod +x run.sh
#
#	Done! Now Every Time, You just have to type only
#			:-$ ./run.sh
#
#
#
#
#
# compile every time
echo "[-] Re-Compiling Source Code"
javac ./CodeRail/*.java



# compile this oue also
echo "[-] Re-Compiling execute file"
javac execute.java


# execute
echo "[-] Executing .."
java execute
