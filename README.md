#Excel Data Extractor

##What is it?
[Excel Data Extractor](https://github.com/iredpath/Excel-Data-Extractor) is a pure JAVA application developed for the use of researchers in Northeastern University’s Lifespan Emotion Development (LED) lab.  Experiments in this lab yield extremely large excel workbook output files, filled with primarily unnecessary data.  The few useful pieces of information have previously been extracted manually, either by tedious copy-paste repetition or through the use of macros.  Excel Data Extractor serves to alleviate both the tediousness and errors caused by these two procedures by providing a simple and intuitive user interface to cleanly extract and output relevant pieces of data.

##How do I use it?
Please see the [User’s Manual](/guide/Users-Manual.pdf) for usage instructions.

##How can I build the application?
Excel Data Extractor uses [Maven](https://maven.apache.org/) as a project management tool to effectively control dependencies and build settings.  The website provides details instructions for installing this tool and creating a suitable environment for its use.  Currently, there are only two plugins of consequence used in this project:

* test: run all unit tests associated with the project
* install: create the executable jar (assuming successful run of test)

The install plugin will create, among other things, the executable jar in the target/ directory of the project.  It can then be transferred to any java running computer (likely 1.6 and above, though this is not confirmed) and launched.

##How can I contribute?
This project is no longer in active development by the core team.  However, anyone may freely fork the repository and make edits as they see fit.  Please [contact us](mailto:iredpath@hotmail.com) with any questions or issues.
