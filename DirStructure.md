#
# ========================================================================
#                  Java Modules Structure Documentation
# ========================================================================
#

To Create Package In Java, First Create A Folder 'ExampleProject', Then Create A Subfolder name ExampleModule.
Here, ExampleProject is Our ( Main | Root ) Directory That Will Contain All File and Folders Related To This Project.
And ExampleModule is Subdirectory. That Will Contain Module Files.

# ===================================================================
#		ExampleModule source file .java template
# ===================================================================
# 
package ExampleModule;
public class Anything{
}

#   Key Point to remember
1. Module Source file can contain only one public class and as many as private classes.
2. Name Of Source Module File and Public class have to be same.
3. Compile This Module From Parent Directory Like This javac ./ExampleModule/source.java
4. Can Run Modules Seperately. java ExampleModule.source

# Now You Can Create Another main setup.java file, into ExampleProject.
# Example of Calling Functions from ExampleModule.
#







