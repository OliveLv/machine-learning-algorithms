JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	KMeansMain.java \
	KernelKMeansMain.java \
	Utils.java \
	KMeans.java \
	KernelKMeans.java \
	SupervisedEvaluation.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
